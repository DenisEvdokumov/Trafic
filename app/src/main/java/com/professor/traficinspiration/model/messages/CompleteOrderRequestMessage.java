package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompleteOrderRequestMessage extends RequestMessage{
    @SerializedName("idSession")
    @Expose
    private String idSession;
    @SerializedName("idSession_MAC")
    @Expose
    private String idSessionMAC;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("action_MAC")
    @Expose
    private String actionMAC;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("sequence_MAC")
    @Expose
    private String sequenceMAC;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_MAC")
    @Expose
    private String idMAC;
    @SerializedName("idOrder")
    @Expose
    private String idOrder;
    @SerializedName("idOrder_MAC")
    @Expose
    private String idOrderMAC;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("review_MAC")
    @Expose
    private String reviewMAC;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("token_MAC")
    @Expose
    private String tokenMAC;

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getIdSessionMAC() {
        return idSessionMAC;
    }

    public void setIdSessionMAC(String idSessionMAC) {
        this.idSessionMAC = idSessionMAC;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionMAC() {
        return actionMAC;
    }

    public void setActionMAC(String actionMAC) {
        this.actionMAC = actionMAC;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequenceMAC() {
        return sequenceMAC;
    }

    public void setSequenceMAC(String sequenceMAC) {
        this.sequenceMAC = sequenceMAC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMAC() {
        return idMAC;
    }

    public void setIdMAC(String idMAC) {
        this.idMAC = idMAC;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdOrderMAC() {
        return idOrderMAC;
    }

    public void setIdOrderMAC(String idOrderMAC) {
        this.idOrderMAC = idOrderMAC;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewMAC() {
        return reviewMAC;
    }

    public void setReviewMAC(String reviewMAC) {
        this.reviewMAC = reviewMAC;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenMAC() {
        return tokenMAC;
    }

    public void setTokenMAC(String tokenMAC) {
        this.tokenMAC = tokenMAC;
    }
}
