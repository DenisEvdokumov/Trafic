package com.professor.traficinspiration.model;


import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.professor.traficinspiration.ApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class UserResponseMessage {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("balance")
    private double balance;

    @SerializedName("orders_completed")
    private long ordersCompleted;

    @SerializedName("referrals_count")
    private long referralsCount;

    @SerializedName("token")
    private String token;

//    @SerializedName("email")
//    private String[] email;

    @SerializedName("errors")
    private Map<String, String[]> errors;

    /**
     * No args constructor for use in serialization
     */
    public UserResponseMessage() {
    }

    /**
     * @param ordersCompleted
     * @param id
     * @param balance
     * @param referralsCount
     */
    public UserResponseMessage(long id, double balance, long ordersCompleted, long referralsCount) {
        super();
        this.id = id;
        this.balance = balance;
        this.ordersCompleted = ordersCompleted;
        this.referralsCount = referralsCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserResponseMessage withId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserResponseMessage withBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public long getOrdersCompleted() {
        return ordersCompleted;
    }

    public void setOrdersCompleted(long ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
    }

    public UserResponseMessage withOrdersCompleted(long ordersCompleted) {
        this.ordersCompleted = ordersCompleted;
        return this;
    }

    public long getReferralsCount() {
        return referralsCount;
    }

    public void setReferralsCount(long referralsCount) {
        this.referralsCount = referralsCount;
    }

    public UserResponseMessage withReferralsCount(long referralsCount) {
        this.referralsCount = referralsCount;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public String[] getEmail() {
//        return email;
//    }
//
//    public void setEmail(String[] email) {
//        this.email = email;
//    }


    public Map<String, String[]> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String[]> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "UserResponseMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", ordersCompleted=" + ordersCompleted +
                ", referralsCount=" + referralsCount +
                ", token='" + token + '\'' +
//                ", email=" + Arrays.toString(email) +
                '}';
    }
}