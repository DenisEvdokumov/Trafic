package com.professor.traficinspiration.service;


import com.professor.traficinspiration.model.messages.UserRequestMessage;
import com.professor.traficinspiration.model.messages.UserResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

//    @GET("accounts/{accountId}")
//    Call<User> getUser(@Path("accountId") long accountId);
//
//    // debug
//    @GET("http://tapmoney.testmy.tk/rest/accinfo?id=48&token=79018549d6318a9e63e0451f85a25bec")
//    Call<UserResponseMessage> getUser();
//
//    @POST("accounts")
//    Call<Long> createUser(@Body User user);

    @POST("accounts")
    Call<UserResponseMessage> getOrCreateUser(@Body UserRequestMessage userRequestMessage);

}
