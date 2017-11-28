package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public abstract class RequestMessage {

    @SerializedName("id")
    private long userId;

    @SerializedName("token")
    private String token;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
