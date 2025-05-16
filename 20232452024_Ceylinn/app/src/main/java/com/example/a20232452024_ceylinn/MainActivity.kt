package com.example.a20232452024_ceylinn

import KullaniciVeritabaniYardimcisi
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etkullaniciadi=findViewById<EditText>(R.id.etkullaniciadi)
        val etsifre=findViewById<EditText>(R.id.etsifre)
        val btngiris=findViewById<Button>(R.id.btngiris)
        val btnkayit=findViewById<Button>(R.id.btnkayit)
        etkullaniciadi.text.clear()
        etsifre.text.clear()
        val db = KullaniciVeritabaniYardimcisi(this)

        btngiris.setOnClickListener {
            val kullaniciAdi = etkullaniciadi.text.toString()
            val sifre = etsifre.text.toString()

            if (db.girisBilgileriniKontrolEt(kullaniciAdi, sifre)) {
                Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, AnaSayfaActivity::class.java)
                intent.putExtra("kullaniciAdi", kullaniciAdi)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show()
            }
        }

        btnkayit.setOnClickListener {
            startActivity(Intent(this, KayitActivity::class.java))
        }

    }
}