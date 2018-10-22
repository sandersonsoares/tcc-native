package br.com.anative.tcc.tcc_native.api.services

import br.com.anative.tcc.tcc_native.api.dto.AccountDTO
import br.com.anative.tcc.tcc_native.api.dto.LoginDTO
import br.com.anative.tcc.tcc_native.api.response.AuthResponse
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IAuthService {

    @POST("/api/auth")
    fun authentication(@Body login: LoginDTO): Call<AuthResponse>

    @GET("/api/public/register")
    fun registerAccount(@Body account: AccountDTO): Call<DefaultResponse>

}