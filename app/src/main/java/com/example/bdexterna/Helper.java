package com.example.bdexterna;


import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Helper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "ejemplo.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "emails";
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_NOMBRE = "nombre";
    public static final String DATABASE_COLUMN_EMAIL = "email";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
