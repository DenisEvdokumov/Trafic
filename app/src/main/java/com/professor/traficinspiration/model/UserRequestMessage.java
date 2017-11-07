package com.professor.traficinspiration.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequestMessage {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("id_referrer")
    @Expose
    private Long idReferrer;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserRequestMessage() {
    }

    /**
     *
     * @param email
     * @param idReferrer
     * @param action
     * @param password
     */
    public UserRequestMessage(String email, String password, String action, Long idReferrer) {
        super();
        this.email = email;
        this.password = password;
        this.action = action;
        this.idReferrer = idReferrer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRequestMessage withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequestMessage withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public UserRequestMessage withAction(String action) {
        this.action = action;
        return this;
    }

    public Long getIdReferrer() {
        return idReferrer;
    }

    public void setIdReferrer(Long idReferrer) {
        this.idReferrer = idReferrer;
    }

    public UserRequestMessage withIdReferrer(long idReferrer) {
        this.idReferrer = idReferrer;
        return this;
    }
}
