package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public class SupportRequestMessage extends RequestMessage{
    @SerializedName("msg")
    private String message;

    public SupportRequestMessage() {
    }

    public SupportRequestMessage(long userId, String token, String message) {
        setUserId(userId);
        setToken(token);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
