package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public class CompleteOrderRequestMessage extends RequestMessage{
    @SerializedName("id_order")
    private long orderId;

    @SerializedName("review")
    private boolean review;

    public CompleteOrderRequestMessage() {
    }

    public CompleteOrderRequestMessage(long userId, String token, long orderId) {
        setUserId(userId);
        setToken(token);
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }
}
