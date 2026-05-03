package com.example.gestionsimpledestudiants.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gestionsimpledestudiants.classes.Etudiant;
import com.example.gestionsimpledestudiants.util.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class EtudiantService {

    private SQLiteDatabase db;
    private MySQLiteHelper helper;

    public EtudiantService(Context context) {
        helper = new MySQLiteHelper(context);
    }

    public void create(Etudiant e) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nom", e.getNom());
        values.put("prenom", e.getPrenom());

        db.insert("etudiant", null, values);
    }

    public Etudiant findById(int id) {
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM etudiant WHERE id=?",
                new String[]{String.valueOf(id)});

        if (c.moveToFirst()) {
            return new Etudiant(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)
            );
        }
        return null;
    }

    public void delete(int id) {
        db = helper.getWritableDatabase();
        db.delete("etudiant", "id=?", new String[]{String.valueOf(id)});
    }
}