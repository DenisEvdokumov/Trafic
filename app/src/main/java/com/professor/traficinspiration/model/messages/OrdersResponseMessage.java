package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.professor.traficinspiration.model.Order;

import java.util.List;
import java.util.Map;

public class OrdersResponseMessage extends ResponseMessage{

    @SerializedName("orders")
    @Expose
    private List<OrderResponse> orderResponses;
    @SerializedName("KeyMAC")
    @Expose
    private String keyMAC;
    @SerializedName("KeyMAC_MAC")
    @Expose
    private String keyMACMAC;

    public List<OrderResponse> getOrders() {
        return orderResponses;
    }

    public void setOrders(List<OrderResponse> orders) {
        this.orderResponses = orders;
    }

    public String getKeyMAC() {
        return keyMAC;
    }

    public void setKeyMAC(String keyMAC) {
        this.keyMAC = keyMAC;
    }

    public String getKeyMACMAC() {
        return keyMACMAC;
    }

    public void setKeyMACMAC(String keyMACMAC) {
        this.keyMACMAC = keyMACMAC;
    }

}

