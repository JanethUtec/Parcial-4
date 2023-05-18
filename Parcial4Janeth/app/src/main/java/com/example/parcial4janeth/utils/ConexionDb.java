package com.example.parcial4janeth.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionDb extends SQLiteOpenHelper {

    public ConexionDb(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.VERSION_SQL);
        System.out.println("========================Creando base de datos");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("========================Creando tablas");
        sqLiteDatabase.execSQL(Constants.CREATE_TABLE_CLI);
        sqLiteDatabase.execSQL(Constants.CREATE_TABLE_VEHI);
        sqLiteDatabase.execSQL(Constants.CREATE_TABLE_CLI_VEHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Constants.DROP_TABLE_CLI);
        sqLiteDatabase.execSQL(Constants.DROP_TABLE_VEHI);
        sqLiteDatabase.execSQL(Constants.DROP_TABLE_CLI_VEHI);

        onCreate(sqLiteDatabase);
    }
}
