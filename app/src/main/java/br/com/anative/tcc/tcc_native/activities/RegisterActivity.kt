package br.com.anative.tcc.tcc_native.activities

import android.os.Bundle
import android.view.MenuItem
import br.com.anative.tcc.tcc_native.R
import br.com.anative.tcc.tcc_native.api.response.DefaultResponse
import br.com.anative.tcc.tcc_native.api.response.ICallbackResponse
import br.com.anative.tcc.tcc_native.api.services.AuthService
import br.com.anative.tcc.tcc_native.model.Account
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.indeterminateProgressDialog

class RegisterActivity : DefaultActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_register.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        var progressbar = indeterminateProgressDialog("Carregando...")
        progressbar.setCancelable(false)
        progressbar.setCanceledOnTouchOutside(false)
        progressbar.show()

        var account = Account(
            name.text.toString(),
            email.text.toString(),
            senha.text.toString()
        )
        AuthService().registerAccount(account, object : ICallbackResponse<DefaultResponse> {
            override fun success(instance: DefaultResponse) {
                progressbar.dismiss()
                if (instance.code.equals("000")) {
                    toast(instance.message.toString())
                } else {
                    toast(instance.message.toString())
                }
            }

            override fun error(instance: DefaultResponse) {
                progressbar.dismiss()
                toast(instance.message.toString())
            }
        }, this)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
