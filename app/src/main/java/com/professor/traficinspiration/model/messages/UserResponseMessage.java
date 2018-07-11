package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UserResponseMessage extends ResponseMessage{
    @SerializedName("KeyMAC")
    private String keyMAC;
    @SerializedName("KeyMAC_MAC")
    private String keyMACMAC;
    @SerializedName("id")
    private String id;
    @SerializedName("id_MAC")
    private String idMAC;
    @SerializedName("balance")
    private String balance;
    @SerializedName("balance_MAC")
    private String balanceMAC;
    @SerializedName("ref_income")
    private String refIncome;
    @SerializedName("ref_income_MAC")
    private String refIncomeMAC;
    @SerializedName("token")
    private String token;
    @SerializedName("token_MAC")
    private String tokenMAC;
    @SerializedName("orders_completed")
    private String ordersCompleted;
    @SerializedName("orders_completed_MAC")
    private String ordersCompletedMAC;
    @SerializedName("referrals_count")
    private String referralsCount;
    @SerializedName("referrals_count_MAC")
    private String referralsCountMAC;
    /**
     * No args constructor for use in serialization
     */

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMAC() {
        return idMAC;
    }

    public void setIdMAC(String idMAC) {
        this.idMAC = idMAC;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalanceMAC() {
        return balanceMAC;
    }

    public void setBalanceMAC(String balanceMAC) {
        this.balanceMAC = balanceMAC;
    }

    public String getRefIncome() {
        return refIncome;
    }

    public void setRefIncome(String refIncome) {
        this.refIncome = refIncome;
    }

    public String getRefIncomeMAC() {
        return refIncomeMAC;
    }

    public void setRefIncomeMAC(String refIncomeMAC) {
        this.refIncomeMAC = refIncomeMAC;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenMAC() {
        return tokenMAC;
    }

    public void setTokenMAC(String tokenMAC) {
        this.tokenMAC = tokenMAC;
    }

    public String getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(String ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public String getOrdersCompletedMAC() {
        return ordersCompletedMAC;
    }

    public void setOrdersCompletedMAC(String ordersCompletedMAC) {
        this.ordersCompletedMAC = ordersCompletedMAC;
    }

    public String getReferralsCount() {
        return referralsCount;
    }

    public void setReferralsCount(String referralsCount) {
        this.referralsCount = referralsCount;
    }

    public String getReferralsCountMAC() {
        return referralsCountMAC;
    }

    public void setReferralsCountMAC(String referralsCountMAC) {
        this.referralsCountMAC = referralsCountMAC;
    }
}
