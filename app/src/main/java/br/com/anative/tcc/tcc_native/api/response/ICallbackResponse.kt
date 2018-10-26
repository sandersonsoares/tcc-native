package br.com.anative.tcc.tcc_native.api.response

interface ICallbackResponse<T> {
    fun success(instance: T)
    fun error(instance: T)
}