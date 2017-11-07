package com.professor.traficinspiration.service;


import com.professor.traficinspiration.TestErrorMessage;
import com.professor.traficinspiration.model.CompleteOrderRequest;
import com.professor.traficinspiration.model.CompleteOrderResponse;
import com.professor.traficinspiration.model.GetOrdersResponse;
import com.professor.traficinspiration.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @GET("clientorders")
    Call<GetOrdersResponse> getOrders(
            @Query("id") long userId,
            @Query("token") String token
    );

    @GET("completedorders")
    Call<GetOrdersResponse> getOrdersHistory(
            @Query("id") long userId,
            @Query("token") String token
    );

    @POST("completedorders")
    Call<CompleteOrderResponse> completeOrder(@Body CompleteOrderRequest completeOrderRequest);
}
