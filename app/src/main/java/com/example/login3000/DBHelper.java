package com.example.login3000;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static String nome = "BancoDados.db";
    private static int versao = 1;
    private String email;

    public DBHelper(Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE utilizador(email TEXT PRIMARY KEY, username TEXT, password TEXT);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utilizador;");
        onCreate(db);
    }

    public long criarUtilizador(String email, String userName, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("username", userName);
        cv.put("password", password);
        long result = db.insert("utilizador", null, cv);
        return result;
    }

    public String validarLogin(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from utilizador where email=? and password=?", new String[]{email, password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }

    public String checarEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from utilizador where email=?" , new String[]{email});
        if (c.getCount() > 0) {
            return"ERRO";
        }
        return "OK";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
