package com.example.ivandimitrov.namevalidator;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<UserRegister> {

    private final Activity context;
    private ArrayList<UserRegister> userList = new ArrayList<UserRegister>();

    public CustomListAdapter(Activity context, ArrayList<UserRegister> itemName) {
        super(context, R.layout.filter_results, itemName);
        this.context = context;
        this.userList = itemName;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.filter_results, null, true);
            viewHolder.txtTitle = (TextView) view.findViewById(R.id.label);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.txtTitle.setText(userList.get(position).getUserName());
        return view;
    }

    static class ViewHolder {
        TextView txtTitle;
    }
}
