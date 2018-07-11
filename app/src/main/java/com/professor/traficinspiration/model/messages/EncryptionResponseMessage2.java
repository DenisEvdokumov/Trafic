package com.professor.traficinspiration.model.messages;

import com.google.gson.annotations.SerializedName;

public class EncryptionResponseMessage2 extends ResponseMessage {

    @SerializedName("keysConfirm")
    private Integer keysConfirm;

    @SerializedName("KeyMAC")
    private String keyMAC;

    @SerializedName("KeyMAC_MAC")
    private String keyMAC_MAC;





    public Integer getKeysConfirm() {
        return keysConfirm;
    }

    public void setKeysConfirm(Integer keysConfirm) {
        this.keysConfirm = keysConfirm;
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

    public void setKeyMAC_MAC(String keyMACMAC) {
        this.keyMAC_MAC = keyMACMAC;
    }



}

