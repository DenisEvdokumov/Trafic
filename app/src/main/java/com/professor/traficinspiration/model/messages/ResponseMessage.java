package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public abstract class ResponseMessage {
    @SerializedName("errors")
    private Map<String, String[]> errors;


    public Map<String, String[]> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String[]> errors) {
        this.errors = errors;
    }
}
