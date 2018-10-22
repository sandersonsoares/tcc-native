package br.com.anative.tcc.tcc_native.api.interceptors

import android.content.Context
import br.com.anative.tcc.tcc_native.model.Auth
import br.com.anative.tcc.tcc_native.util.SharedPreferencesUtil
import com.orm.SugarRecord
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class APIInterceptor(context: Context) : Interceptor {

    private val AUTHORIZATION_HEADER = "Authorization"
    private val URLS_DEFAULTS = arrayOf(
        "/api/auth",
        "/api/public/register"
    )
    private val sharedPreferencesUtil = SharedPreferencesUtil(context)

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val token = SugarRecord.listAll(Auth::class.java).get(0).token.toString()

        val request: Request? = if (URLS_DEFAULTS.contains(chain?.request()!!.url().encodedPath())) {
            chain.request()!!.newBuilder().build()
        } else {
            chain.request()!!.newBuilder().addHeader(AUTHORIZATION_HEADER, sharedPreferencesUtil.token).build()
        }
        return chain.proceed(request)
    }
}