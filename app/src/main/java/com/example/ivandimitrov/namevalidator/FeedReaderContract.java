package com.example.ivandimitrov.namevalidator;

import android.provider.BaseColumns;

/**
 * Created by Ivan Dimitrov on 12/23/2016.
 */

public final class FeedReaderContract {
    private FeedReaderContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME        = "register";
        public static final String COLUMN_USERNAME   = "Username";
        public static final String COLUMN_FIRST_NAME = "FirstName";
        public static final String COLUMN_LAST_NAME  = "LastName";
        public static final String COLUMN_EMAIL      = "Email";
        public static final String COLUMN_PASSWORD   = "Password";
    }
}
