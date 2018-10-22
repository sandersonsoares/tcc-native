package br.com.anative.tcc.tcc_native.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.dto.LoginDTO
import br.com.anative.tcc.tcc_native.api.response.AuthResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.services.AuthService
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : DefaultActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { efetuarLogin() }
        btn_register.setOnClickListener { abrirRegistro() }
    }

    private fun efetuarLogin() {
        progressbar.visibility = ProgressBar.VISIBLE
        var login = LoginDTO(login.text.toString(), senha.text.toString())
        AuthService().authentication(login, object : ICallbackResponse<AuthResponse> {
            override fun success(instance: AuthResponse) {
                progressbar.visibility = ProgressBar.INVISIBLE
                if (instance.code.equals("000")) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else {
                    toast(instance.message.toString())
                }
            }
        }, this)

    }

    private fun abrirRegistro() {
        startActivityForResult(Intent(this, RegisterActivity::class.java), 0)
    }
}
