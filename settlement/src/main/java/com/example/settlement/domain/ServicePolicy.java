package com.example.settlement.domain;

import lombok.Getter;

@Getter
public enum ServicePolicy {
    A(1L, "/settlement/services/a", 10),
    B(2L, "/settlement/services/b", 10),
    C(3L, "/settlement/services/c", 10),
    D(4L, "/settlement/services/d", 15),
    E(5L, "/settlement/services/e", 15),
    F(6L, "/settlement/services/f", 10),
    G(7L, "/settlement/services/g", 10),
    H(8L, "/settlement/services/h", 10),
    I(9L, "/settlement/services/i", 10),
    J(10L, "/settlement/services/j", 10),
    K(11L, "/settlement/services/k", 10),
    L(12L, "/settlement/services/l", 12),
    M(13L, "/settlement/services/m", 12),
    N(14L, "/settlement/services/n", 12),
    O(15L, "/settlement/services/o", 10),
    P(16L, "/settlement/services/p", 10),
    Q(17L, "/settlement/services/q", 10),
    R(18L, "/settlement/services/r", 10),
    S(19L, "/settlement/services/s", 10),
    T(20L, "/settlement/services/t", 10),
    U(21L, "/settlement/services/u", 10),
    V(22L, "/settlement/services/v", 10),
    W(23L, "/settlement/services/w", 19),
    X(24L, "/settlement/services/x", 19),
    Y(25L, "/settlement/services/y", 19),
    Z(26L, "/settlement/services/z", 19);

    private final Long id;
    private final String url;
    private final Integer fee;

    ServicePolicy(Long id, String url, Integer fee) {
        this.id = id;
        this.url = url;
        this.fee = fee;
    }
}
