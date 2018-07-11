package com.professor.traficinspiration.model;

public class EncryptKeys {

    private String string;
    private String string_MAC;
    private Long idSession;
    private String idSession_MAC;
    private String keyMAC;
    private String keyMAC_MAC;


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
        return keyMAC;
    }

    public void setKeyMAC(String keyMAC) {
        this.keyMAC = keyMAC;
    }

    public String getKeyMAC_MAC() {
        return keyMAC_MAC;
    }

    public void setKeyMAC_MAC(String keyMAC_MAC) {
        this.keyMAC_MAC = keyMAC_MAC;
    }
}
