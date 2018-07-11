package com.professor.traficinspiration.model.messages;


import com.google.gson.annotations.SerializedName;


public class EncryptionResponseMessage extends ResponseMessage {

    @SerializedName("string")
    private String string ;

    @SerializedName("string_MAC")
    private String string_MAC;

    @SerializedName("idSession")
    private Long idSession;

    @SerializedName("idSession_MAC")
    private String idSession_MAC;

    @SerializedName("KeyMAC")
    private String KeyMAC;


    @SerializedName("KeyMAC_MAC")
    private String KeyMAC_MAC;


    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString_MAC() {
        return string_MAC;
    }

    public void setString_MAC(String string_MAC) {
        this.string_MAC = string_MAC;
    }

    public Long getIdSession() {
        return idSession;
    }

    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }

    public String getIdSession_MAC() {
        return idSession_MAC;
    }

    public void setIdSession_MAC(String idSession_MAC) {
        this.idSession_MAC = idSession_MAC;
    }

    public String getKeyMAC() {
        return KeyMAC;
    }

    public void setKeyMAC(String keyMAC) {
        KeyMAC = keyMAC;
    }

    public String getKeyMAC_MAC() {
        return KeyMAC_MAC;
    }

    public void setKeyMAC_MAC(String keyMAC_MAC) {
        KeyMAC_MAC = keyMAC_MAC;
    }
}
