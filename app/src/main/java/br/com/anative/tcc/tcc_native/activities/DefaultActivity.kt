package br.com.anative.tcc.tcc_native.activities

import android.support.v7.app.AppCompatActivity
import android.widget.Toast


open class DefaultActivity : AppCompatActivity() {

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}