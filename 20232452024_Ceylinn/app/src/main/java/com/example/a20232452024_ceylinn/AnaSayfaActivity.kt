package com.example.a20232452024_ceylinn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.CalendarView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class AnaSayfaActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_sayfa)
        havaDurumunuGetir()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "PARSVAN KARAVAN"

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Anasayfa", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_features -> {
                    startActivity(Intent(this,OzelliklerActivity::class.java))
                }

                R.id.nav_price -> {
                    startActivity(Intent(this,FiyatActivitiy::class.java))
                }

                R.id.nav_rental -> {
                    startActivity(Intent(this, KiralamaActivity::class.java))

                }

                R.id.nav_contact -> {
                    startActivity(Intent(this,IletisimActivity::class.java))
                }

                R.id.nav_logout -> {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawers()
            true
        }


        val kullaniciAdi = intent.getStringExtra("kullaniciAdi")


        val tvHosGeldin = findViewById<TextView>(R.id.tvHosGeldin)
        tvHosGeldin.text = "Hoş geldiniz, $kullaniciAdi!"
        tvHosGeldin.textSize = 20f
        tvHosGeldin.setPadding(0, 0, 0, 32)


        val takvim = findViewById<CalendarView>(R.id.calendarView)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin = 120
        takvim.layoutParams = layoutParams

        takvim.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val tarih = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(this, "Seçilen: $tarih", Toast.LENGTH_SHORT).show()
        }


    }

    private fun havaDurumunuGetir() {
        val apiKey ="68cd2be18304373a05285439ec1a8572"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Aydin,tr&units=metric&lang=tr&appid=$apiKey"

        val client = okhttp3.OkHttpClient()
        val request = okhttp3.Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("HAVA_DURUMU", "Bağlantı hatası: ${e.message}", e)
                runOnUiThread {
                    Toast.makeText(this@AnaSayfaActivity, "Hava durumu alınamadı", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.body?.string()?.let { json ->
                    val obj = org.json.JSONObject(json)
                    val sicaklik = obj.getJSONObject("main").getDouble("temp")
                    val aciklama = obj.getJSONArray("weather").getJSONObject(0).getString("description")

                    runOnUiThread {
                        val tvHavaDurumu = findViewById<TextView>(R.id.tvHavaDurumu)
                        tvHavaDurumu.text = "Aydın: $sicaklik°C – $aciklama"
                    }
                }
            }
        })
    }
}