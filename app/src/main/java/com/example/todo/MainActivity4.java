package com.example.todo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity4 extends AppCompatActivity {
    EditText work;
    Button takadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences addtask= getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor taskeditor=addtask.edit();
        Set<String> set = addtask.getStringSet("todo", new HashSet<>());
        ArrayList<String> todo = new ArrayList<>(set);
        work=findViewById(R.id.editTextText2);
        takadd=findViewById(R.id.button4);
        takadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(work.length()>0){
                    int sr=todo.size()+1;
                    todo.add(sr+"."+work.getText().toString());
                    Set<String> set = new HashSet<>(todo);
                    taskeditor.putStringSet("todo",set);
                    taskeditor.apply();
                    Toast.makeText(MainActivity4.this, "Task added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity4.this, "Enter a task to add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}