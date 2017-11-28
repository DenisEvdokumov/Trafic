package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WithdrawResponseMessage extends ResponseMessage{
    @SerializedName("msg")
    private String  message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
