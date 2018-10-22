package br.com.anative.tcc.tcc_native.api.services

import android.content.Context
import android.util.Log
import br.com.anative.tcc.tcc_native.api.RetrofitInitializer
import br.com.anative.tcc.tcc_native.api.dto.AccountDTO
import br.com.anative.tcc.tcc_native.api.dto.LoginDTO
import br.com.anative.tcc.tcc_native.api.response.AuthResponse
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.util.SharedPreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {

    val TAG = "AUTH-SERVICE"

    fun authentication(login: LoginDTO, iCallbackResponse: ICallbackResponse<AuthResponse>? = null, context: Context) {

        val service = RetrofitInitializer().authService(context)
        val call = service.authentication(login)

        call.enqueue(object : Callback<AuthResponse?> {

            override fun onResponse(call: Call<AuthResponse?>?, response: Response<AuthResponse?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val authResponse: AuthResponse = it

                        if (authResponse.code.equals("000")){
                            SharedPreferencesUtil(context).token = authResponse.token
                        }

                        iCallbackResponse.success(authResponse)
                    }
                }

                Log.i(TAG, "[INFO] authentication sucessfull")
            }

            override fun onFailure(call: Call<AuthResponse?>?, t: Throwable?) {
                Log.i(TAG, "[ERROR] authentication error")
            }
        })
    }

    fun registerAccount(
        account: AccountDTO,
        iCallbackResponse: ICallbackResponse<DefaultResponse>? = null,
        context: Context
    ) {

        val service = RetrofitInitializer().authService(context)
        val call = service.registerAccount(account)

        call.enqueue(object : Callback<DefaultResponse?> {

            override fun onResponse(call: Call<DefaultResponse?>?, response: Response<DefaultResponse?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val defaultResponse: DefaultResponse = it
                        iCallbackResponse.success(defaultResponse)
                    }
                }

                Log.i(TAG, "[INFO] registerAccount sucessfull")
            }

            override fun onFailure(call: Call<DefaultResponse?>?, t: Throwable?) {
                Log.i(TAG, "[ERROR] registerAccount error")
            }
        })
    }
}