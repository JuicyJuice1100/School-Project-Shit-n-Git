package com.example.juice.labapplication;

import android.provider.BaseColumns;

public final class LocalDatabaseContract {

    public static final class User implements BaseColumns{
        public static String TABLE_NAME = "user";
        public static String COLUMN_USERNAME = "username";
        public static String COLUMN_PASSWORD = "password";
    }
}
