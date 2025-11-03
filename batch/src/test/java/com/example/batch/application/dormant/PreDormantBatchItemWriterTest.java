package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PreDormantBatchItemWriterTest {

    private PreDormantBatchItemWriter preDormantBatchItemWriter;

    @Test
    @DisplayName("일주일 뒤에 휴면계정전환 예정자라고 이메일을 전송해야 한다.")
    void test1() {

        // given
        final EmailProvider mockEmailProvider = mock(EmailProvider.class);
        this.preDormantBatchItemWriter = new PreDormantBatchItemWriter(mockEmailProvider);

        final Customer customer = new Customer("aaa", "aaa@gmail.com");

        // when
        preDormantBatchItemWriter.write(customer);

        // then
        verify(mockEmailProvider, atLeastOnce()).send(any(), any(), any());
    }
}