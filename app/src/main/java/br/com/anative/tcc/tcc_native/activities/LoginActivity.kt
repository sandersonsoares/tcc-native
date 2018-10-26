package br.com.anative.tcc.tcc_native.activities

import android.content.Intent
import android.os.Bundle
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.response.AuthResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.services.AuthService
import br.com.anative.tcc.tcc_native.model.Login
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.indeterminateProgressDialog


class LoginActivity : DefaultActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_login.setOnClickListener { efetuarLogin() }
        btn_register.setOnClickListener { abrirRegistro() }
    }

    private fun efetuarLogin() {
        var progressbar = indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        var login = Login(login.text.toString(), senha.text.toString())
        AuthService().authentication(login, object : ICallbackResponse<AuthResponse> {

            override fun success(instance: AuthResponse) {
                progressbar.dismiss()
                if (instance.code.equals("000")) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else {
                    toast(instance.message.toString())
                }
            }

            override fun error(instance: AuthResponse) {
                progressbar.dismiss()
                toast(instance.message.toString())
            }
        }, this)

    }

    private fun abrirRegistro() {
        startActivityForResult(Intent(this, RegisterActivity::class.java), 0)
    }
}
