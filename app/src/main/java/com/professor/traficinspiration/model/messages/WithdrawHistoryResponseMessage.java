package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;
import com.professor.traficinspiration.model.WithdrawHistoryEntry;

import java.util.List;

public class WithdrawHistoryResponseMessage extends ResponseMessage {
    @SerializedName("list")
    private List<WithdrawHistoryEntry> withdrawList;

    public List<WithdrawHistoryEntry> getWithdrawList() {
        return withdrawList;
    }

    public void setWithdrawList(List<WithdrawHistoryEntry> withdrawList) {
        this.withdrawList = withdrawList;
    }
}
