package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequestMessage extends RequestMessage{

    @SerializedName("idSession")
    @Expose
    private String idSession;
    @SerializedName("idSession_MAC")
    @Expose
    private String idSessionMAC;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("sequence")
    @Expose
    private String sequence;
    @SerializedName("sequence_MAC")
    @Expose
    private String sequenceMAC;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_MAC")
    @Expose
    private String emailMAC;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_MAC")
    @Expose
    private String passwordMAC;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailMAC() {
        return emailMAC;
    }

    public void setEmailMAC(String emailMAC) {
        this.emailMAC = emailMAC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMAC() {
        return passwordMAC;
    }

    public void setPasswordMAC(String passwordMAC) {
        this.passwordMAC = passwordMAC;
    }

}
