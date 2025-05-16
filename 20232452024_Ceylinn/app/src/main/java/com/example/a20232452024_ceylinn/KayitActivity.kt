package com.example.a20232452024_ceylinn

import KullaniciVeritabaniYardimcisi
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KayitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kayit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etkayitkullanici=findViewById<EditText>(R.id.etkayitkullanici)
        val etkayitsifre=findViewById<EditText>(R.id.etkayitsifre)
        val butonkayit=findViewById<Button>(R.id.butonkayit)



        val db = KullaniciVeritabaniYardimcisi(this)


        butonkayit.setOnClickListener {
            val kullaniciAdi = etkayitkullanici.text.toString()
            val sifre = etkayitsifre.text.toString()

            if (kullaniciAdi.isNotEmpty() && sifre.isNotEmpty()) {
                val eklendi = db.kullaniciEkle(kullaniciAdi, sifre)
                if (eklendi) {
                    Toast.makeText(this, "Kayıt başarılı", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Kayıt başarısız, kullanıcı adı alınmış olabilir", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Boş alan bırakmayın", Toast.LENGTH_SHORT).show()
            }
        }

    }
}