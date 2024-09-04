package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Button button;
    ArrayList<String> spin=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner=findViewById(R.id.spinner);
        button=findViewById(R.id.button);
        spin.add("View Todo");
        spin.add("Create new Todo");
        spin.add("Add to todo");
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item ,spin);
        spinner.setAdapter(spinnerAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosen=spinner.getSelectedItem().toString();
                if(chosen=="View Todo"){
                    Toast.makeText(MainActivity.this, "selected view", Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent1);
                }
                else if(chosen=="Create new Todo"){
                    Toast.makeText(MainActivity.this, "selected create", Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(MainActivity.this, "selected add", Toast.LENGTH_SHORT).show();
                    Intent intent3 =new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent3);
                }
            }
        });



    }
}