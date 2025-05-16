package com.example.a20232452024_ceylinn

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KiralamaVeritabaniYardimcisi(context: Context) :
    SQLiteOpenHelper(context, "KiralamaVeritabani.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Kiralama(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                adsoyad TEXT,
                telefon TEXT,
                model TEXT,
                tarih TEXT,
                yer TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Kiralama")
        onCreate(db)
    }

    fun kaydet(ad: String, tel: String, model: String, tarih: String, yer: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("adsoyad", ad)
            put("telefon", tel)
            put("model", model)
            put("tarih", tarih)
            put("yer", yer)
        }
        val sonuc = db.insert("Kiralama", null, values)
        return sonuc != -1L
    }
}