package com.example.a20232452024_ceylinn

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KiralamaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kiralama)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = KiralamaVeritabaniYardimcisi(this)
        val etAdSoyad = findViewById<EditText>(R.id.etadsoyad)
        val etTelefon = findViewById<EditText>(R.id.ettelefon)
        val etModel = findViewById<EditText>(R.id.etmodel)
        val etTarih = findViewById<EditText>(R.id.ettarih)
        val etYer = findViewById<EditText>(R.id.etyer)
        val btnKirala = findViewById<Button>(R.id.btnkirala)
        val btntemizle=findViewById<Button>(R.id.btntemizle)




            btntemizle.setOnClickListener {
                etAdSoyad.text.clear()
                etTelefon.text.clear()
                etModel.text.clear()
                etTarih.text.clear()
                etYer.text.clear()
                Toast.makeText(this, "Form temizlendi", Toast.LENGTH_SHORT).show()
            }




        etTarih.setOnClickListener {
            val calendar = Calendar.getInstance()
            val yil = calendar.get(Calendar.YEAR)
            val ay = calendar.get(Calendar.MONTH)
            val gun = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, g ->
                etTarih.setText("$g/${m + 1}/$y")
            }, yil, ay, gun)


            val yarin = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_MONTH, 1)
            }
            datePicker.datePicker.minDate = yarin.timeInMillis


            val birAySonra = Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
            }
            datePicker.datePicker.maxDate = birAySonra.timeInMillis

            datePicker.show()
        }
        btnKirala.setOnClickListener {
            val ad = etAdSoyad.text.toString()
            val tel = etTelefon.text.toString()
            val model = etModel.text.toString()
            val tarih = etTarih.text.toString()
            val yer = etYer.text.toString()

            if (ad.isNotEmpty() && tel.isNotEmpty() && model.isNotEmpty() && tarih.isNotEmpty() && yer.isNotEmpty()) {
                val kayitBasarili = db.kaydet(ad, tel, model, tarih, yer)
                if (kayitBasarili) {
                    Toast.makeText(this, "Veritabanına kaydedildi ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Kayıt başarısız ", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}