package com.professor.traficinspiration.service;


import com.professor.traficinspiration.model.messages.CompleteOrderRequestMessage;
import com.professor.traficinspiration.model.messages.CompleteOrderResponseMessage;
import com.professor.traficinspiration.model.messages.OrdersResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("clientorders")
    Call<OrdersResponseMessage> getOrders(
            @Query("id") long userId,
            @Query("token") String token
    );

    @GET("completedorders")
    Call<OrdersResponseMessage> getOrdersHistory(
            @Query("id") long userId,
            @Query("token") String token
    );

    @POST("completedorders")
    Call<CompleteOrderResponseMessage> completeOrder(@Body CompleteOrderRequestMessage completeOrderRequestMessage);
}
