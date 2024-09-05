package com.example.todo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {
    ListView listView, listView1;
    SharedPreferences erpo;
    ArrayList<String> todolist;
    ArrayList<String> completed;
    ArrayAdapter<String> completedAdapter;

    private void saveColorPreference(int position, String color) {
        SharedPreferences.Editor editor = erpo.edit();
        editor.putString("color_" + position, color);
        editor.apply();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        erpo = getSharedPreferences("data", MODE_PRIVATE);
        Set<String> set = erpo.getStringSet("todo", new HashSet<>());
        todolist = new ArrayList<>(set);
        Collections.sort(todolist);
        completed = new ArrayList<>(erpo.getStringSet("completed", new HashSet<>()));

        listView = findViewById(R.id.listview);
        listView1 = findViewById(R.id.listview1);

        completedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, completed);
        listView1.setAdapter(completedAdapter);

        TodoAdapter adapter = new TodoAdapter(this, todolist, erpo);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
            alert.setTitle("TODO");
            alert.setMessage("Choose the option");
            alert.setPositiveButton("Done", (dialogInterface, which) -> {
                String item = todolist.get(i); // Get the item from the list
                view.setBackgroundColor(Color.GREEN);
                saveColorPreference(i, "GREEN");
                if (!completed.contains(item)) {
                    completed.add(item);
                    Set<String> setCompleted = new HashSet<>(completed);
                    SharedPreferences.Editor editor = erpo.edit();
                    editor.putStringSet("completed", setCompleted);
                    editor.apply();
                    completedAdapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged(); // Refresh the todo list
            });
            alert.setNegativeButton("Pending", (dialogInterface, which) -> {
                view.setBackgroundColor(Color.RED);
                saveColorPreference(i, "RED");
                adapter.notifyDataSetChanged(); // Refresh the todo list
            });
            alert.show();
        });
    }
}
