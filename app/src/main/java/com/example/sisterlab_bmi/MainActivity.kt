package com.example.sisterlab_bmi

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Login sayfamızda intent ile göndermiş olduğumuz kullanıcı adını bu sayfada almamız gerekiyor. (Texview tanımına gerek yok)
        //getstringextra ile gelen veriyi, geldiği sayfada verdiğimiz key e göre değişkene atayıp çağırıyoruz.
        //Textview'in setText metodundan yazarlanarak ekrana yazdırıyoruz.
        var twUserName = findViewById(R.id.twUserName) as TextView
        val valueName = intent.getStringExtra("UserName")
        twUserName.setText("Merhaba "+ valueName + "!")

       initialize()
    }

    private fun initialize() {

        var etHeight = findViewById(R.id.etHeight) as EditText
        var etWeight = findViewById(R.id.etWeight) as EditText
        var tvResult = findViewById(R.id.tvResult) as TextView
        var tvBMI = findViewById(R.id.tvBMI) as TextView
        var btnCalculate = findViewById(R.id.btnCalculate) as Button

        btnCalculate.setOnClickListener{
            //height ve weight değişkenlerini double (ondalıklı olarak tanımlıyoruz)
            var height : Double = etHeight.text.toString().toDouble()
            val weight : Double = etWeight.text.toString().toDouble()
            var result = 0.0
            
            //kilo ve boyumuzun 0'dan büyük olması durumunda hesaplama işlemine başlıyoruz. Eğer 0'dan küçük ise hata mesajı verecektir.
            if(height>0 && weight>0) {
                //bu iki satır vücut kütle indeksi formülü 
                height = height / 100
                result = (weight / (height * height))
                //çıkan sonucu ondalıklı olarak uygun formata çeviriyoruz. %.2f: virgülden sonra 2 basamağa kadar gösterir
                val roundedResult = String.format("%.2f", result)
                tvBMI.setText(roundedResult.toString())

                //when() in...  : Belli aralık verilerek o aralıkta gerçekleşecek işlemleri gerçekleştirmeyi sağlar
                //when(result): result değişkeninde yer alan değeri alıp koşullara baka.
                when (result){

                    //result değeri 0.0 ile 18.50 değeri arasında ise;
                    //ekrana basılacak metini gösterir ve sayfanın layoutunun arkaplan rengini değiştirir.
                    in 0.0..18.50 ->
                    {
                        tvResult.text = "Zayıf!"
                        screenLayout.setBackgroundColor(Color.BLUE)
                    }
                    in 18.51..24.90 ->
                    {
                        tvResult.text = "Normal! "
                        screenLayout.setBackgroundColor(Color.GREEN)
                    }
                    in 25.0..29.90 ->
                    {
                        tvResult.text = "Kilolu! "
                        screenLayout.setBackgroundColor(Color.YELLOW)
                    }
                    in 30.0..34.90 ->
                    {
                        tvResult.text = "Obez! "
                        screenLayout.setBackgroundColor(Color.MAGENTA)
                    }
                    //yukarıdaki aralıklardan farklı bir sonuç geldiyse aşağıdaki sonucu verir
                    else ->
                    {
                        tvResult.text = "Aşırı Obez! "
                        screenLayout.setBackgroundColor(Color.RED)
                    }
                }
    }
            else
            {
                //alert hata,bilgi, uyarı mesajların verildiği penceredir.
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Hata!") //başlığın ne olacağı belirlenir.
                alert.setMessage("Boy ve kilonuz 0 dan büyük olmalı") //verilecek mesaj yazılır
                alert.setCancelable(false); 
                alert.setPositiveButton("Tamam") { dialogInterface: DialogInterface, i: Int -> } //buton üzerinde yazılacak metin verilir.
                alert.show() //ekrana götermeyi sağlar
            }
        }
    }
}
