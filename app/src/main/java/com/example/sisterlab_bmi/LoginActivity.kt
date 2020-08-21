package com.example.sisterlab_bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    var userName = "Esra"
    var password = "123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

    }

    private fun initialize() {
        var etUserName = findViewById(R.id.et_user_name) as EditText
        var etPassword = findViewById(R.id.et_password) as EditText
        var btnLogin = findViewById(R.id.btn_login) as Button

        btnLogin.setOnClickListener {
            if (isEmpty(etUserName.text)) {
                etUserName.setError("Kullanıcı adı boş geçilemez!");
            }
            if (isEmpty(etPassword.text)) {
                etPassword.setError("Şife boş geçilemez!");
            }
           if (etUserName.text.contains(userName) && etPassword.text.contains(password))
            {
                val gecis = Intent(this,MainActivity::class.java)
                gecis.putExtra("UserName", etUserName.text.toString())
                startActivity(gecis)
            }
            else
            {
                val Toast = Toast.makeText(applicationContext,"Giriş Hatalı",Toast.LENGTH_SHORT)
                Toast.show()
            }
        }
    }
}