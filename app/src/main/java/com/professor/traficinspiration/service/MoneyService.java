package com.professor.traficinspiration.service;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MoneyService {

    @FormUrlEncoded
    @POST("/money")
    Call<Boolean> transferMoney(
            @Field("amount") int amount,
            @Field("accountId") long userId,
            @Field("destinationType") int destinationType,
            @Field("destinationNumber") long destinationNumber
    );
}
