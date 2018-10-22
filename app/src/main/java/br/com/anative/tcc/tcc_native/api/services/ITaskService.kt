package br.com.anative.tcc.tcc_native.api.services

import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.model.Task
import retrofit2.Call
import retrofit2.http.*

interface ITaskService {

    @POST("/api/task")
    fun create(@Body task: Task): Call<DefaultResponse>

    @PUT("/api/task")
    fun update(@Body task: Task): Call<DefaultResponse>

    @DELETE("/api/task/{id}")
    fun delete(@Path("id") id: String): Call<DefaultResponse>

    @GET("/api/task/finished")
    fun listFinished(): Call<TasksResponse>

    @GET("/api/task")
    fun listAll(): Call<TasksResponse>

    @GET("/api/task/todo")
    fun listToDo(): Call<TasksResponse>

}