package com.professor.traficinspiration.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GetOrdersResponse {

    @SerializedName("orders")
    private List<Order> orderList;

    @SerializedName("errors")
    private Map<String, String[]> errors;

    public GetOrdersResponse() {
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Map<String, String[]> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String[]> errors) {
        this.errors = errors;
    }
}
