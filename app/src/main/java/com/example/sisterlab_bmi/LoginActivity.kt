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
        //aşağıdaki tanımlamaları yapmamız şart değil, kotlin tanımlama yapmadan da kolaylık sağlıyor 
        var etUserName = findViewById(R.id.et_user_name) as EditText
        var etPassword = findViewById(R.id.et_password) as EditText
        var btnLogin = findViewById(R.id.btn_login) as Button

        //butonun tıklanabilme özelliği
        btnLogin.setOnClickListener {
            //if yapısı: eğer koşul gerçeklendiğinde ne olacak, gerçekleşmediğinde ne olacak? sorularına karşılık veren yapıdır.
            //isempty() ona verilen değerin boş olup olmadığını kontrol eder.
            //seterror: satırda hata mesajı vermeye yarar.
            
            //kulanıcı adını boş olup olmamasına baktık, eğer boş ise hata mesajı çıkacaktır.
            if (isEmpty(etUserName.text)) {
                etUserName.setError("Kullanıcı adı boş geçilemez!");
            }
            
            //şifrenin boş olup olmamasına baktık, eğer boş ise hata mesajı çıkacaktır.
            if (isEmpty(etPassword.text)) {
                etPassword.setError("Şife boş geçilemez!");
            }
            
            //contains: text ifadeler üzerinde karşılaştırma yapılacak ise kullanlır. 
            //burada kullanıcının girdiği değer ile en yukarıda global olarak tanımlanan userName ve password değişkenini karşılatırıyoruz.
            //Sonuç true(doğru) ise: sayfa geçişi olacak, sonuç false (yanlış) ise toast mesajı ile ekrana hata basılacaktır.
           if (etUserName.text.contains(userName) && etPassword.text.contains(password))
            {
                //sayfalar arası geçişlerde ve veri taşımalarda INTENT kullanılır.
                //gecis değişkenine intenti tanımlıyoruz ve hangi activity ye gideceğini belirliyoruz
                //putextra veri taşımada bize yardımcı olacak mettodur. Key,value şeklinde değer alır. 
                // Key: anahtar görevi görür ve taşınacak verinin diğer sayfada kullanabilmeyi sağlar.
                // Value: anahtar ile hangi değerin taşınacağını belirtir.
                //StartActivity ise intentte bahsedilen sayfanın açılmasını sağlar.
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
