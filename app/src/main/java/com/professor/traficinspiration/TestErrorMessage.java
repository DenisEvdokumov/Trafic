package com.professor.traficinspiration;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestErrorMessage {

    @SerializedName("error")
    @Expose
    private String error;

    public TestErrorMessage() {
    }

    public TestErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "TestErrorMessage{" +
                "error='" + error + '\'' +
                '}';
    }
}
