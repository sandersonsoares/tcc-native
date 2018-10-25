package br.com.anative.tcc.tcc_native.activities

import android.content.Intent
import android.os.Bundle
import br.com.anative.tcc.tcc_native.util.SharedPreferencesUtil

class MainActiviy : DefaultActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var sharedPreferencesUtil = SharedPreferencesUtil(this)
        if (sharedPreferencesUtil.token != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}