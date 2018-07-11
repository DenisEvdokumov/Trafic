package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;

public class EncryptionRequestMessage  {


    @SerializedName("sequence")
    private String sequence;

    @SerializedName("sequence_MAC")
    private String sequenceMAC;

    @SerializedName("action")
    private String action;

    @SerializedName("encryptedKeyAES")
    private String encryptedKeyAES;

    @SerializedName("encryptedKeyMAC")
    private String encryptedKeyMAC;



    public EncryptionRequestMessage(String action, String sequence, String sequenceMAC, String encryptedKeyAES, String encryptedKeyMAC) {
        this.action = action;
        this.sequence = sequence;
        this.sequenceMAC = sequenceMAC;
        this.encryptedKeyAES = encryptedKeyAES;
        this.encryptedKeyMAC = encryptedKeyMAC;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getEncryptedKeyAES() {
        return encryptedKeyAES;
    }

    public void setEncryptedKeyAES(String encryptedKeyAES) {
        this.encryptedKeyAES = encryptedKeyAES;
    }

    public String getEncryptedKeyMAC() {
        return encryptedKeyMAC;
    }

    public void setEncryptedKeyMAC(String encryptedKeyMAC) {
        this.encryptedKeyMAC = encryptedKeyMAC;
    }
}

