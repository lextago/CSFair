package com.example.alextagocsfair;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_screen);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView body = findViewById(R.id.textView9);

        String body_text =
                "2023 CS Fair" + "\n" +
                "App made by Alex Tago" + "\n" + "\n" + "\n" +
                "Data collected from" + "\n" +
                "https://www.wildflower.org/" + "\n" + "\n" + "\n" +
                "Credits to:" + "\n" + "\n" +
                "https://www.flaticon.com for icons" + "\n" +
                "- Plant Icon created by Aziz Muttaqin" + "\n" +
                "- Gradient Icon created by Graphics Plazza"
                ;

        body.setText(body_text);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}
