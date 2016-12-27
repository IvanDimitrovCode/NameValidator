package com.example.ivandimitrov.namevalidator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Ivan Dimitrov on 12/27/2016.
 */

public class FilteringActivity extends Activity {
    private CheckBox mUsernameBox;
    private CheckBox mFirstNameBox;
    private CheckBox mLastNameBox;
    private CheckBox mEmailBox;
    private ArrayList<String> mFilters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Button filterButton = (Button) findViewById(R.id.filterButton2);
        initResources();

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsernameBox.isChecked() && (!mFilters.contains(FeedReaderContract.FeedEntry.COLUMN_USERNAME))) {
                    mFilters.add(FeedReaderContract.FeedEntry.COLUMN_USERNAME);
                }
                if (mFirstNameBox.isChecked() && (!mFilters.contains(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME))) {
                    mFilters.add(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME);
                }
                if (mLastNameBox.isChecked() && (!mFilters.contains(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME))) {
                    mFilters.add(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME);
                }
                if (mEmailBox.isChecked() && (!mFilters.contains(FeedReaderContract.FeedEntry.COLUMN_EMAIL))) {
                    mFilters.add(FeedReaderContract.FeedEntry.COLUMN_EMAIL);
                }
            }
        });
    }

    private void initResources() {
        mUsernameBox = (CheckBox) findViewById(R.id.boxUsername);
        mFirstNameBox = (CheckBox) findViewById(R.id.boxFirstName);
        mLastNameBox = (CheckBox) findViewById(R.id.boxLastName);
        mEmailBox = (CheckBox) findViewById(R.id.boxEmail);
    }
}
