package br.com.anative.tcc.tcc_native.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.dto.AccountDTO
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.services.AuthService
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : DefaultActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_register.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        progressbar.visibility = ProgressBar.VISIBLE
        var account = AccountDTO(name.text.toString(), email.text.toString(), senha.text.toString())
        AuthService().registerAccount(account, object : ICallbackResponse<DefaultResponse> {
            override fun success(instance: DefaultResponse) {
                progressbar.visibility = ProgressBar.INVISIBLE
                if (instance.code.equals("000")) {
                    toast(instance.message.toString())
                    finish()
                } else {
                    toast(instance.message.toString())
                }
            }
        }, this)
    }

}
