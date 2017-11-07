package com.professor.traficinspiration.model;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CompleteOrderResponse {

    @SerializedName("errors")
    private Map<String, String[]> errors;

    public CompleteOrderResponse() {
    }

    public CompleteOrderResponse(Map<String, String[]> errors) {
        this.errors = errors;
    }

    public Map<String, String[]> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String[]> errors) {
        this.errors = errors;
    }
}
