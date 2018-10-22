package br.com.anative.tcc.tcc_native.api

import android.content.Context
import br.com.anative.tcc.tcc_native.api.interceptors.APIInterceptor
import br.com.anative.tcc.tcc_native.api.services.IAuthService
import br.com.anative.tcc.tcc_native.api.services.ITaskService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val BASE_URL = "https://todo-task-api.herokuapp.com/"

    fun client(context: Context): OkHttpClient {
        var client = OkHttpClient()
        client.newBuilder().addInterceptor(APIInterceptor(context)).build()
        return client
    }

    fun build(context: Context) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client(context))
        .build()

    fun authService(context: Context) = build(context).create(IAuthService::class.java)

    fun taskService(context: Context) = build(context).create(ITaskService::class.java)
}