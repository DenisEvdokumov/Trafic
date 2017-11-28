package com.professor.traficinspiration.service;


import com.professor.traficinspiration.model.messages.WithdrawRequestMessage;
import com.professor.traficinspiration.model.messages.WithdrawResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoneyService {

    @POST("withdraw")
    Call<WithdrawResponseMessage> withdraw(@Body WithdrawRequestMessage withdrawRequestMessage);
}
