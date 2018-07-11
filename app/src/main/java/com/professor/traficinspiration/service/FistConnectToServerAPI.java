package com.professor.traficinspiration.service;

import com.professor.traficinspiration.model.messages.EncryptionRequestMessage;
import com.professor.traficinspiration.model.messages.EncryptionRequestMessage2;
import com.professor.traficinspiration.model.messages.EncryptionResponseMessage;
import com.professor.traficinspiration.model.messages.EncryptionResponseMessage2;
import com.professor.traficinspiration.model.messages.UserRequestMessage;
import com.professor.traficinspiration.model.messages.UserResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface FistConnectToServerAPI {


    @POST("keyex")
    Call<EncryptionResponseMessage2> getFirstKey2(@Body EncryptionRequestMessage2 encryptionRequestMessage2);


    @POST("keyex")
    Call<EncryptionResponseMessage> getFirstKey(@Body EncryptionRequestMessage encryptionRequestMessage);
}
