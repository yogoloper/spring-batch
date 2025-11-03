package com.example.batch.application;

import com.example.batch.EmailProvider;
import com.example.batch.batch.Tasklet;
import com.example.batch.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchTaskLet implements Tasklet {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchTaskLet(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void excute() {

        int pageNo = 0;

        // 비즈니스 로직
        while (true) {

                // 1. 유저를 조회한다.
                final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").ascending());
                final Page<Customer> page = customerRepository.findAll(pageRequest);

                final Customer customer;
                if (page.isEmpty()) {
                    break;
                } else {
                    pageNo++;
                    customer = page.getContent().get(0);
                }

                // 2. 휴면 계정 대상을 추출 및 변환한다.
                final boolean isDormantTarget = LocalDate.now()
                        .minusDays(365)
                        .isAfter(customer.getLoginAt().toLocalDate());

                if (isDormantTarget) {
                    customer.setStatus(Customer.Status.DORMANT);
                } else {
                    continue;
                }

                // 3. 휴면 계정으로 상태를 변경 한다.
                customerRepository.save(customer);

                // 4. 메일을 보낸다.
                emailProvider.send(customer.getEmail(), "휴면 전환 안내메일 입니다.", "내용");
            }
    }
}
