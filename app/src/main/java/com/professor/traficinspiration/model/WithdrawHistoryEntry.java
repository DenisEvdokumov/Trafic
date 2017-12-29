package com.professor.traficinspiration.model;


import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

public class WithdrawHistoryEntry {

    @SerializedName("created_at")
    private long requestDate;

    @SerializedName("updated_at")
    private long updateDate;

    @SerializedName("sum")
    private long amount;

    @SerializedName("type")
    private String withdrawType;

    @SerializedName("acc")
    private String accountNumber;

    @SerializedName("notice")
    private String notice;

    private int status;

    public WithdrawHistoryEntry() {
    }

    public Date getRequestDate() {
        return new Date(requestDate*1000);
    }

    public void setRequestDate(long requestDate) {
        this.requestDate = requestDate;
    }

    public Date getUpdateDate() {
        return new Date(updateDate*1000);
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
