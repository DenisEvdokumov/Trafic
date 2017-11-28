package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;
import com.professor.traficinspiration.model.Order;

import java.util.List;
import java.util.Map;

public class OrdersResponseMessage extends ResponseMessage{
    @SerializedName("orders")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
