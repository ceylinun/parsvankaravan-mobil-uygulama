package com.example.a20232452024_ceylinn

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FiyatActivitiy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fiyat_activitiy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rbmoto = findViewById<RadioButton>(R.id.rbmoto)
        val rbcekme = findViewById<RadioButton>(R.id.rbcekme)
        val rbtiny = findViewById<RadioButton>(R.id.rbtiny)

        val rbkusadasi = findViewById<RadioButton>(R.id.rbkusadasi)
        val rbguzelcamli = findViewById<RadioButton>(R.id.rbguzelcamli)

        val rbhaftaici = findViewById<RadioButton>(R.id.rbhaftaici)
        val rbhaftasonu = findViewById<RadioButton>(R.id.rbhaftasonu)

        val btnFiyat = findViewById<Button>(R.id.btnfiyat)
        val tvSonuc = findViewById<TextView>(R.id.etsonuc)

        val btnsifirla=findViewById<Button>(R.id.btnsifirla)

        btnsifirla.setOnClickListener {
            findViewById<RadioGroup>(R.id.radioGroup).clearCheck()
            findViewById<RadioGroup>(R.id.radioGroup2).clearCheck()
            findViewById<RadioGroup>(R.id.radioGroup3).clearCheck()
            tvSonuc.text = ""
        }



        btnFiyat.setOnClickListener {
            var fiyat = 1000
            if (rbmoto.isChecked) fiyat += 500
            else if (rbcekme.isChecked) fiyat += 300
            else if (rbtiny.isChecked) fiyat += 700


            if (rbkusadasi.isChecked) fiyat += 200
            else if (rbguzelcamli.isChecked) fiyat += 250


            if (rbhaftaici.isChecked) fiyat += 100
            else if (rbhaftasonu.isChecked) fiyat += 200

            tvSonuc.text = "Tahmini Fiyat: $fiyat TL"
        }
        }



    }
