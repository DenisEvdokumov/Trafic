package com.professor.traficinspiration.model.messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdersRequestMessage {
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
    private String action_MAC;
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
    @SerializedName("token")
    @Expose
    private String token;

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

    public String getAction_MAC() {
        return action_MAC;
    }

    public void setAction_MAC(String action_MAC) {
        this.action_MAC = action_MAC;
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

    @SerializedName("token_MAC")
    @Expose
    private String tokenMAC;
}
