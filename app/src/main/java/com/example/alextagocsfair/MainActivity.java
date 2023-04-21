package com.example.alextagocsfair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.textView);
        EditText ed1 = findViewById(R.id.editTextTextPersonName);
        EditText ed2 = findViewById(R.id.editTextTextPersonName2);
        Button bt1 = findViewById(R.id.button);
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#283618"));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                i.putExtra("username", username);
                i.putExtra("password", password);

                startActivity(i);

            }
        });

    }
}