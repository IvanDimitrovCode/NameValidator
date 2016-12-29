package com.example.ivandimitrov.namevalidator;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Dimitrov on 12/27/2016.
 */

public class FilteringActivity extends Activity {
    private CheckBox mUsernameBox;
    private CheckBox mFirstNameBox;
    private CheckBox mLastNameBox;
    private CheckBox mEmailBox;

    private EditText mUsernameFilter;
    private EditText mFirstNameFilter;
    private EditText mLastNameFilter;
    private EditText mEmailFilter;


    private RadioButton buttonCombined;
    private RadioButton mButtonSeparate;

    private ArrayList<EditText> mFilterList = new ArrayList<>();
    private ArrayList<CheckBox> mBoxList    = new ArrayList<>();

    private ArrayList<UserRegister> itemIds = new ArrayList<>();
    private FeedReaderDbHelper mDbHelper;
    private EditText           mainEditText;
    private CustomListAdapter  mAdapter;
    private ListView           mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        Button filterButton = (Button) findViewById(R.id.filterButton2);
        mainEditText = (EditText) findViewById(R.id.filterArgument);

        initResources();

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedColumns = new ArrayList<String>();
                if (isGlobalSearch()) {
                    if (mUsernameBox.isChecked()) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_USERNAME);
                    }
                    if (mFirstNameBox.isChecked()) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME);
                    }
                    if (mLastNameBox.isChecked()) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME);
                    }
                    if (mEmailBox.isChecked()) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_EMAIL);
                    }
                } else {
                    if (mUsernameBox.isChecked() && !mUsernameFilter.getText().toString().equals("")) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_USERNAME);
                    }
                    if (mFirstNameBox.isChecked() && !mFirstNameFilter.getText().toString().equals("")) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME);
                    }
                    if (mLastNameBox.isChecked() && !mLastNameFilter.getText().toString().equals("")) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_LAST_NAME);
                    }
                    if (mEmailBox.isChecked() && !mEmailFilter.getText().toString().equals("")) {
                        selectedColumns.add(FeedReaderContract.FeedEntry.COLUMN_EMAIL);
                    }
                }
                getUserFromDatabase(selectedColumns);
            }
        });

        mAdapter = new CustomListAdapter(this, itemIds);
        mListView = (ListView) this.findViewById(R.id.filterList);
        mListView.setAdapter(mAdapter);
//        listView.setOnItemClickListener(listener);
    }

    private void getUserFromDatabase(List<String> columnsSelected) {
        if (!isValidSearch(columnsSelected)) {
            return;
        }

        String selectedSearchingLogic;
        if (isGlobalSearch()) {
            selectedSearchingLogic = "OR";
        } else {
            selectedSearchingLogic = (buttonCombined.isChecked() ? "AND" : "OR");
        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] tableColumns = {FeedReaderContract.FeedEntry.COLUMN_USERNAME,
                FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME,
                FeedReaderContract.FeedEntry.COLUMN_LAST_NAME,
                FeedReaderContract.FeedEntry.COLUMN_EMAIL};

        String filterArgument = mainEditText.getText().toString();
        String whereClause = "";
        String[] whereArgs = new String[columnsSelected.size()];

        // WHERE CLAUSE
        for (int i = 0; i < columnsSelected.size(); i++) {
            if (i == columnsSelected.size() - 1) {
                whereClause += String.format("%s = ?", columnsSelected.get(i));
            } else {
                whereClause += String.format("%s = ? %s ", columnsSelected.get(i), selectedSearchingLogic);
            }
        }

        // WHERE ARGUMENT
        if (isGlobalSearch()) {
            for (int i = 0; i < columnsSelected.size(); i++) {
                whereArgs[i] = filterArgument;
            }
        } else {
            for (int i = 0, argIndex = 0; i < mFilterList.size(); i++) {
                if (!mFilterList.get(i).getText().toString().equals("") && mBoxList.get(i).isChecked()) {
                    whereArgs[argIndex] = mFilterList.get(i).getText().toString();
                    argIndex++;
                }
            }
        }

//        for (int i = 0, argIndex = 0; i < filterList.size(); i++) {
//            if (!isExtendedSearch() || !filterList.get(i).getText().toString().equals("") && boxList.get(i).isChecked()) {
//                whereArgs[argIndex] = (isExtendedSearch() ? filterArgument : filterList.get(i).getText().toString());
//                argIndex++;
//            }
//        }

        // SORTING ORDER
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_FIRST_NAME + " DESC";
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                sortOrder
        );

        itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserRegister newUser = new UserRegister();
            String s = cursor.getString(cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_USERNAME));
            newUser.setUserName(s);
            itemIds.add(newUser);
        }

        mAdapter.clear();
        mAdapter.addAll(itemIds);
        mAdapter.notifyDataSetChanged();

        Log.d("FILTER", itemIds.toString());
        cursor.close();
    }

    private boolean isValidSearch(List<String> columnsSelected) {
        if (columnsSelected.size() < 1) {
            Toast.makeText(this, getString(R.string.filterNotSelected), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isGlobalSearch() {
        if (mainEditText.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    private void initResources() {
        // CHECKBOX SETUP
        mUsernameBox = (CheckBox) findViewById(R.id.boxUsername);
        mFirstNameBox = (CheckBox) findViewById(R.id.boxFirstName);
        mLastNameBox = (CheckBox) findViewById(R.id.boxLastName);
        mEmailBox = (CheckBox) findViewById(R.id.boxEmail);

        // FILTER SETUP
        mUsernameFilter = (EditText) findViewById(R.id.filterUsername);
        mUsernameFilter.setEnabled(false);

        mFirstNameFilter = (EditText) findViewById(R.id.filterFirstName);
        mFirstNameFilter.setEnabled(false);

        mLastNameFilter = (EditText) findViewById(R.id.filterLastName);
        mLastNameFilter.setEnabled(false);

        mEmailFilter = (EditText) findViewById(R.id.filterEmail);
        mEmailFilter.setEnabled(false);

        // CHECKBOX LISTENER SETUP
        mUsernameBox.setOnClickListener(getBoxListeners(mUsernameFilter, mUsernameBox));
        mFirstNameBox.setOnClickListener(getBoxListeners(mFirstNameFilter, mFirstNameBox));
        mLastNameBox.setOnClickListener(getBoxListeners(mLastNameFilter, mLastNameBox));
        mEmailBox.setOnClickListener(getBoxListeners(mEmailFilter, mEmailBox));

        mFilterList.add(mUsernameFilter);
        mFilterList.add(mFirstNameFilter);
        mFilterList.add(mLastNameFilter);
        mFilterList.add(mEmailFilter);

        mBoxList.add(mUsernameBox);
        mBoxList.add(mFirstNameBox);
        mBoxList.add(mLastNameBox);
        mBoxList.add(mEmailBox);

        buttonCombined = (RadioButton) findViewById(R.id.buttonCombined);
        mButtonSeparate = (RadioButton) findViewById(R.id.buttonSeparate);
    }


    private View.OnClickListener getBoxListeners(EditText filter, CheckBox box) {
        final EditText tempFilter = filter;
        final CheckBox tempBox = box;

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tempBox.isChecked()) {
                    tempFilter.setEnabled(true);
                } else {
                    tempFilter.setEnabled(false);
                }
            }
        };
    }
}
