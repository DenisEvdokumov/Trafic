package com.professor.traficinspiration.service;


import com.professor.traficinspiration.model.messages.OrdersResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawHistoryResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawRequestMessage;
import com.professor.traficinspiration.model.messages.WithdrawResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MoneyService {

    @POST("withdraw")
    Call<WithdrawResponseMessage> withdraw(@Body WithdrawRequestMessage withdrawRequestMessage);

    @GET("withdraw")
    Call<WithdrawHistoryResponseMessage> getWithdrawHistory(
            @Query("id") long userId,
            @Query("token") String token
    );
}
