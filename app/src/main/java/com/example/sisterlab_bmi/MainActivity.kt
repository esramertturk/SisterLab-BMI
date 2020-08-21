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
            var height : Double = etHeight.text.toString().toDouble()
            val weight : Double = etWeight.text.toString().toDouble()
            var result = 0.0

            if(height>0 && weight>0) {
                height = height / 100
                result = (weight / (height * height))
                val roundedResult = String.format("%.2f", result)
                tvBMI.setText(roundedResult.toString())

                when (result){

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
                    else ->
                    {
                        tvResult.text = "Aşırı Obez! "
                        screenLayout.setBackgroundColor(Color.RED)
                    }
                }
    }
            else
            {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Hata!")
                alert.setMessage("Boy ve kilonuz 0 dan büyük olmalı")
                alert.setCancelable(false);
                alert.setPositiveButton("Tamam") { dialogInterface: DialogInterface, i: Int -> }
                alert.show()
            }
        }
    }
}