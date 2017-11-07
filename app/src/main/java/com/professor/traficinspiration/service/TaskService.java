package com.professor.traficinspiration.service;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TaskService {

    @FormUrlEncoded
    @POST("/tasks")
    Call<Boolean> completeTask(
            @Field("taskId") long taskId,
            @Field("accountId") long accountId
    );
}
