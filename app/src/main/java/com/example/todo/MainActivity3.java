package com.example.todo;

import android.content.Intent;
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

public class MainActivity3 extends AppCompatActivity {
    ArrayList<String> todo= new ArrayList<>();
    EditText editText;
    Button add;
    Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        add=findViewById(R.id.button2);
        finish=findViewById(R.id.button3);
        editText=findViewById(R.id.editTextText);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.length()>0){
                    String txt=editText.getText().toString();
                    int sr=todo.size()+1;
                    todo.add(sr+"."+txt);
                    editText.setText("");
                    Toast.makeText(MainActivity3.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity3.this, "Please enter task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shrd=getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor=shrd.edit();
                editor.clear();
                Set<String> set = new HashSet<>(todo);
                editor.putStringSet("todo",set);
                editor.apply();
                Toast.makeText(MainActivity3.this, "Todo list created", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(MainActivity3.this, MainActivity2.class);
//                intent.putExtra("todo",todo);
                startActivity(intent);
                onBackPressed();
            }

        });

    }
}