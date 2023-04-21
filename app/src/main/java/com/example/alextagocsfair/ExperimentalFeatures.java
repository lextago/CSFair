package com.example.alextagocsfair;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ExperimentalFeatures extends AppCompatActivity {
    String location;

    String[] appearances = {
            "herb",
            "subshrub",
            "shrub",
            "tree",
            "succulent",
            "grass",
            "fern",
            "vine"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experimental_features);
        TextView expinfo = findViewById(R.id.textView14);

        location = getIntent().getStringExtra("location");

        Toolbar toolbar = findViewById(R.id.toolbarExp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Experimental Features");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(Color.parseColor("#606C38"));
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#606C38"));


        expinfo.setText("Note: Using experimental features may increase the load times significantly.");

        Spinner appr_spinner = findViewById(R.id.appr_spinner);
        Switch appr_switch = findViewById(R.id.appr_switch);
        ArrayAdapter<CharSequence> appr_adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item_text, appearances);
        appr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appr_spinner.setAdapter(appr_adapter);





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.info:
                Intent infotent = new Intent(this, InfoActivity.class);
                startActivity(infotent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }


}
