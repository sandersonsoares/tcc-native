package br.com.anative.tcc.tcc_native.api.services

import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.anative.tcc.tcc_native.activities.LoginActivity
import br.com.anative.tcc.tcc_native.activities.MainActiviy
import br.com.anative.tcc.tcc_native.api.RetrofitInitializer
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.response.TasksResponse
import br.com.anative.tcc.tcc_native.model.Task
import br.com.anative.tcc.tcc_native.util.SharedPreferencesUtil
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskService {

    val TAG = "TASK-SERVICE"

    fun create(
        task: Task,
        iCallbackResponse: ICallbackResponse<DefaultResponse>? = null,
        context: Context
    ) {

        var sharedPreferencesUtil = SharedPreferencesUtil(context)
        val service = RetrofitInitializer().taskService(context)
        val call = service.create(task, sharedPreferencesUtil.token.toString())

        call.enqueue(object : Callback<DefaultResponse?> {

            override fun onResponse(call: Call<DefaultResponse?>?, response: Response<DefaultResponse?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val defaultResponse: DefaultResponse = it
                        iCallbackResponse.success(defaultResponse)
                    }
                    if (response?.code() != 200) {
                        var resposta = TasksResponse(null)
                        resposta.code = response?.code().toString()
                        resposta.message = response?.message().toString()
                        iCallbackResponse.success(resposta)

                        if (response?.code() == 401) {
                            sharedPreferencesUtil.limpar()
                            context.startActivity(Intent(context, MainActiviy::class.java))
                        }
                    }
                }

                Log.i(TAG, "[INFO] create sucessfull")
            }

            override fun onFailure(call: Call<DefaultResponse?>?, t: Throwable?) {
                var resposta = TasksResponse(null)
                resposta.message = t?.message.toString()
                iCallbackResponse?.error(resposta)
                Log.i(TAG, "[ERROR] create error")
            }
        })
    }

    fun update(
        task: Task,
        iCallbackResponse: ICallbackResponse<DefaultResponse>? = null,
        context: Context
    ) {

        var sharedPreferencesUtil = SharedPreferencesUtil(context)
        val service = RetrofitInitializer().taskService(context)
        val call = service.update(task, task._id.toString(), sharedPreferencesUtil.token.toString())

        call.enqueue(object : Callback<DefaultResponse?> {

            override fun onResponse(call: Call<DefaultResponse?>?, response: Response<DefaultResponse?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val defaultResponse: DefaultResponse = it
                        iCallbackResponse.success(defaultResponse)
                    }
                    if (response?.code() != 200) {
                        var resposta = TasksResponse(null)
                        resposta.code = response?.code().toString()
                        resposta.message = response?.message().toString()
                        iCallbackResponse.success(resposta)

                        if (response?.code() == 401) {
                            sharedPreferencesUtil.limpar()
                            context.startActivity(Intent(context, MainActiviy::class.java))
                        }
                    }
                }

                Log.i(TAG, "[INFO] update sucessfull")
            }

            override fun onFailure(call: Call<DefaultResponse?>?, t: Throwable?) {
                var resposta = TasksResponse(null)
                resposta.message = t?.message.toString()
                iCallbackResponse?.error(resposta)
                Log.i(TAG, "[ERROR] update error")
            }
        })
    }

    fun remove(
        task: Task,
        iCallbackResponse: ICallbackResponse<DefaultResponse>? = null,
        context: Context
    ) {

        var sharedPreferencesUtil = SharedPreferencesUtil(context)
        val service = RetrofitInitializer().taskService(context)
        val call = service.delete(task._id.toString(), sharedPreferencesUtil.token.toString())

        call.enqueue(object : Callback<DefaultResponse?> {

            override fun onResponse(call: Call<DefaultResponse?>?, response: Response<DefaultResponse?>?) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        val defaultResponse: DefaultResponse = it
                        iCallbackResponse.success(defaultResponse)
                    }
                    if (response?.code() != 200) {
                        var resposta = TasksResponse(null)
                        resposta.code = response?.code().toString()
                        resposta.message = response?.message().toString()
                        iCallbackResponse.success(resposta)

                        if (response?.code() == 401) {
                            sharedPreferencesUtil.limpar()
                            context.startActivity(Intent(context, MainActiviy::class.java))
                        }
                    }
                }

                Log.i(TAG, "[INFO] remove sucessfull")
            }

            override fun onFailure(call: Call<DefaultResponse?>?, t: Throwable?) {
                var resposta = TasksResponse(null)
                resposta.message = t?.message.toString()
                iCallbackResponse?.error(resposta)
                Log.i(TAG, "[ERROR] remove error")
            }
        })
    }

    fun list(
        iCallbackResponse: ICallbackResponse<TasksResponse>? = null,
        context: Context
    ) {

        var sharedPreferencesUtil = SharedPreferencesUtil(context)
        val service = RetrofitInitializer().taskService(context)
        val call = service.listAll(sharedPreferencesUtil.token.toString())

        call.enqueue(object : Callback<TasksResponse?> {

            override fun onResponse(call: Call<TasksResponse?>?, response: Response<TasksResponse?>) {

                if (iCallbackResponse != null) {
                    response?.body()?.let {
                        iCallbackResponse.success(it)
                    }
                    if (response.code() != 200) {
                        var resposta = TasksResponse(null)
                        resposta.code = response.code().toString()
                        resposta.message = response.message().toString()
                        iCallbackResponse.success(resposta)

                        if (response.code() == 401) {
                            sharedPreferencesUtil.limpar()
                            context.startActivity(Intent(context, MainActiviy::class.java))
                        }
                    }
                }

                Log.i(TAG, "[INFO] list sucessfull")
            }

            override fun onFailure(call: Call<TasksResponse?>?, t: Throwable?) {
                var resposta = TasksResponse(null)
                resposta.message = t?.message.toString()
                iCallbackResponse?.error(resposta)
                Log.i(TAG, "[ERROR] list error")
            }
        })
    }

}