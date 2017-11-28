package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public class WithdrawRequestMessage extends RequestMessage{

    @SerializedName("sum")
    private long amount;

    @SerializedName("notice")
    private boolean notice;

    public WithdrawRequestMessage() {
    }

    public WithdrawRequestMessage(long userId, String token, long amount, boolean notice) {
        setUserId(userId);
        setToken(token);
        this.amount = amount;
        this.notice = notice;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean isNotice() {
        return notice;
    }

    public void setNotice(boolean notice) {
        this.notice = notice;
    }
}
