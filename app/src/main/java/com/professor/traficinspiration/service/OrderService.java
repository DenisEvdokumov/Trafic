package com.professor.traficinspiration.service;


import com.professor.traficinspiration.model.messages.CompleteOrderRequestMessage;
import com.professor.traficinspiration.model.messages.CompleteOrderResponseMessage;
import com.professor.traficinspiration.model.messages.OrdersRequestMessage;
import com.professor.traficinspiration.model.messages.OrdersResponseMessage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @POST("clientorders")
    Call<OrdersResponseMessage> getOrders(@Body OrdersRequestMessage ordersRequestMessage );

    @POST("clientorders")
    Call<OrdersResponseMessage> getOrdersHistory(@Body OrdersRequestMessage ordersRequestMessage);

    @POST("clientorders")
    Call<CompleteOrderResponseMessage> completeOrder(@Body CompleteOrderRequestMessage completeOrderRequestMessage);
}
