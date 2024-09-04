package com.example.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private final SharedPreferences preferences;

    public TodoAdapter(Context context, ArrayList<String> values, SharedPreferences preferences) {
        super(context, android.R.layout.simple_list_item_1, values);
        this.context = context;
        this.values = values;
        this.preferences = preferences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(values.get(position));

        // Load color from preferences
        String color = preferences.getString("color_" + position, "WHITE");
        switch (color) {
            case "GREEN":
                convertView.setBackgroundColor(Color.GREEN);
                break;
            case "RED":
                convertView.setBackgroundColor(Color.RED);
                break;
            default:
                convertView.setBackgroundColor(Color.WHITE);
                break;
        }

        return convertView;
    }
}
