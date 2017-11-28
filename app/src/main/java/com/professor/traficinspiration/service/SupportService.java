package com.professor.traficinspiration.service;

import com.professor.traficinspiration.model.messages.SupportRequestMessage;
import com.professor.traficinspiration.model.messages.SupportResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SupportService {

    @POST("withdraw")
    Call<SupportResponseMessage> sendSupportRequest(@Body SupportRequestMessage supportRequestMessage);
}
