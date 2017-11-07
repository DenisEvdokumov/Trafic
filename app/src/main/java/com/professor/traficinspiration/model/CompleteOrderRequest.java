package com.professor.traficinspiration.model;


import com.google.gson.annotations.SerializedName;

public class CompleteOrderRequest {

    @SerializedName("id")
    private long userId;

    @SerializedName("token")
    private String token;

    @SerializedName("id_order")
    private long orderId;

    public CompleteOrderRequest() {
    }

    public CompleteOrderRequest(long userId, String token, long orderId) {
        this.userId = userId;
        this.token = token;
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}
