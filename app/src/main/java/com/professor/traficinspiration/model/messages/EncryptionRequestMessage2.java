package com.professor.traficinspiration.model.messages;

import com.google.gson.annotations.SerializedName;

public class EncryptionRequestMessage2 {

    @SerializedName("string_MAC")
    private String stringMAC;

    @SerializedName("sequence")
    private String sequence;

    @SerializedName("idSession_MAC")
    private String idSessionMAC;

    @SerializedName("string")
    private String string;

    @SerializedName("keysConfirm")
    private Integer keysConfirm;

    @SerializedName("sequence_MAC")
    private String sequenceMAC;

    @SerializedName("action")
    private String action;

    @SerializedName("idSession")
    private String idSession;


    public String getStringMAC() {
        return stringMAC;
    }

    public void setStringMAC(String stringMAC) {
        this.stringMAC = stringMAC;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getIdSessionMAC() {
        return idSessionMAC;
    }

    public void setIdSessionMAC(String idSessionMAC) {
        this.idSessionMAC = idSessionMAC;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getKeysConfirm() {
        return keysConfirm;
    }

    public void setKeysConfirm(Integer keysConfirm) {
        this.keysConfirm = keysConfirm;
    }

    public String getSequenceMAC() {
        return sequenceMAC;
    }

    public void setSequenceMAC(String sequenceMAC) {
        this.sequenceMAC = sequenceMAC;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }
}
