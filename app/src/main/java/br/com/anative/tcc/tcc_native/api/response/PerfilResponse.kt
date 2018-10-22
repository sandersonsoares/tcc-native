package br.com.anative.tcc.tcc_native.api.response

import br.com.anative.tcc.tcc_native.model.Auth

data class PerfilResponse(
    var auth: Auth? = null
) : DefaultResponse() {
    override fun toString(): String {
        return "PerfilResponse(auth=$auth)"
    }
}