package br.com.anative.tcc.tcc_native.model

import com.orm.SugarRecord

class Auth(
    var token: String? = null
) : SugarRecord<Auth>() {
    override fun toString(): String {
        return "Auth(token=$token)"
    }
}