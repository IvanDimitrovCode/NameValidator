package com.example.ivandimitrov.namevalidator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_USERNAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_LAST_NAME + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_EMAIL + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_PASSWORD + " TEXT);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    private UsernameRegistration mRegistration;
    private SQLiteDatabase       mDataBase;
    private ContentValues        mContentValues;
    private FeedReaderDbHelper   mDbHelper;
    private Activity             mMainActivity;

    private EditText mUsernameEdit;
    private EditText mFirstNameEdit;
    private EditText mLastNameEdit;
    private EditText mEmailEdit;
    private EditText mPasswordEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivity = this;
        initResources();

        mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        mContentValues = new ContentValues();

        mDataBase = mDbHelper.getWritableDatabase();
        mRegistration = new UsernameRegistration(this);

        Button registerButton = (Button) findViewById(R.id.registrationButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRegistration.isRegistrationValid()) {
                    UserRegister newUser = new UserRegister();
                    newUser.setUserName(mUsernameEdit.getText().toString());
                    newUser.setFirstName(mFirstNameEdit.getText().toString());
                    newUser.setLastName(mLastNameEdit.getText().toString());
                    newUser.setEmail(mEmailEdit.getText().toString());
                    newUser.setPassword(mPasswordEdit.getText().toString());

                    addUserToDatabase(newUser);
                }
            }
        });
        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mMainActivity, FilteringActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addUserToDatabase(UserRegister newUser) {
        mContentValues.put(FeedReaderContract.FeedEntry.COLUMN_USERNAME, newUser.getUserName());
        mContentValues.put(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME, newUser.getFirstName());
        mContentValues.put(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME, newUser.getLastName());
        mContentValues.put(FeedReaderContract.FeedEntry.COLUMN_EMAIL, newUser.getEmail());
        mContentValues.put(FeedReaderContract.FeedEntry.COLUMN_PASSWORD, newUser.getPassword());
        long newRowId = mDataBase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, mContentValues);
    }




    @Override
    protected void onPause() {
        super.onPause();
        mRegistration.clearResources();
    }

    private void initResources() {
        mUsernameEdit = (EditText) findViewById(R.id.username);
        mFirstNameEdit = (EditText) findViewById(R.id.firstName);
        mLastNameEdit = (EditText) findViewById(R.id.lastName);
        mEmailEdit = (EditText) findViewById(R.id.email);
        mPasswordEdit = (EditText) findViewById(R.id.password);
    }
}