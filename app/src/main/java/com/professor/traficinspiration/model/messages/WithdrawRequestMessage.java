package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public class WithdrawRequestMessage extends RequestMessage{

    @SerializedName("sum")
    private long amount;

    @SerializedName("type")
    private String withdrawType;

    @SerializedName("acc")
    private String accountNumber;

    @SerializedName("notice")
    private String notice;

    public WithdrawRequestMessage() {
    }

    public WithdrawRequestMessage(long userId, String token, long amount, String withdrawType, String accountNumber, String notice) {
        setUserId(userId);
        setToken(token);
        this.amount = amount;
        this.withdrawType = withdrawType;
        this.accountNumber = accountNumber;
        this.notice = notice;
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
}
