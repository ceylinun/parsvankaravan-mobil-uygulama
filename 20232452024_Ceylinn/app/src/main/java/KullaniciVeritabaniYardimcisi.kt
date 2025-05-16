import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KullaniciVeritabaniYardimcisi(context: Context) : SQLiteOpenHelper(context, "KullaniciVeritabani", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE kullanicilar (id INTEGER PRIMARY KEY AUTOINCREMENT, kullanici_adi TEXT UNIQUE, sifre TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, eskiSurum: Int, yeniSurum: Int) {
        db.execSQL("DROP TABLE IF EXISTS kullanicilar")
        onCreate(db)
    }

    fun kullaniciEkle(kullaniciAdi: String, sifre: String): Boolean {
        val vt = this.writableDatabase
        val veriler = ContentValues()
        veriler.put("kullanici_adi", kullaniciAdi)
        veriler.put("sifre", sifre)
        val sonuc = vt.insert("kullanicilar", null, veriler)
        return sonuc != -1L
    }

    fun girisBilgileriniKontrolEt(kullaniciAdi: String, sifre: String): Boolean {
        val vt = this.readableDatabase
        val sorgu = vt.rawQuery(
            "SELECT * FROM kullanicilar WHERE kullanici_adi=? AND sifre=?",
            arrayOf(kullaniciAdi, sifre)
        )
        val girisBasarili = sorgu.count > 0
        sorgu.close()
        return girisBasarili
    }
}