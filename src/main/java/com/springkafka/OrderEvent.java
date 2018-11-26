package com.springkafka;

import java.util.UUID;

public class OrderEvent {
    private UUID id;
    private String userId;
    private Long productId;

    public OrderEvent() {
        this.id = UUID.randomUUID();
    }

    public OrderEvent(String uid, Long product) {
        this.userId = uid;
        this.productId = product;
        this.id = UUID.randomUUID();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

}