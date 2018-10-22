package br.com.anative.tcc.tcc_native.api.services

import br.com.anative.tcc.tcc_native.model.Account
import br.com.anative.tcc.tcc_native.model.Login
import br.com.anative.tcc.tcc_native.api.response.AuthResponse
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthService {

    @POST("/api/auth")
    fun authentication(@Body login: Login): Call<AuthResponse>

    @POST("/api/public/register")
    fun registerAccount(@Body account: Account): Call<DefaultResponse>

}