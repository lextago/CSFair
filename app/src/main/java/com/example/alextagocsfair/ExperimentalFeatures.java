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

        Spinner appr_spinner = findViewById(R.id.exp_spinner1);
        Switch appr_switch = findViewById(R.id.exp_switch1);
        ArrayAdapter<CharSequence> appr_adapter = ArrayAdapter.createFromResource(this, R.array.appearances, R.layout.spinner_item_text);
        appr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appr_spinner.setAdapter(appr_adapter);

        Spinner dur_spinner = findViewById(R.id.exp_spinner2);
        Switch dur_switch = findViewById(R.id.exp_switch2);
        ArrayAdapter<CharSequence> dur_adapter = ArrayAdapter.createFromResource(this, R.array.duration, R.layout.spinner_item_text);
        dur_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dur_spinner.setAdapter(dur_adapter);

        Spinner light_spinner = findViewById(R.id.exp_spinner3);
        Switch light_switch = findViewById(R.id.exp_switch3);
        ArrayAdapter<CharSequence> light_adapter = ArrayAdapter.createFromResource(this, R.array.light, R.layout.spinner_item_text);
        light_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        light_spinner.setAdapter(light_adapter);

        Spinner moist_spinner = findViewById(R.id.exp_spinner4);
        Switch moist_switch = findViewById(R.id.exp_switch4);
        ArrayAdapter<CharSequence> moist_adapter = ArrayAdapter.createFromResource(this, R.array.moisture, R.layout.spinner_item_text);
        moist_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moist_spinner.setAdapter(moist_adapter);

        Spinner bloom_spinner = findViewById(R.id.exp_spinner5);
        Switch bloom_switch = findViewById(R.id.exp_switch5);
        ArrayAdapter<CharSequence> bloom_adapter = ArrayAdapter.createFromResource(this, R.array.bloom, R.layout.spinner_item_text);
        bloom_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloom_spinner.setAdapter(bloom_adapter);

        Spinner color_spinner = findViewById(R.id.exp_spinner6);
        Switch color_switch = findViewById(R.id.exp_switch6);
        ArrayAdapter<CharSequence> color_adapter = ArrayAdapter.createFromResource(this, R.array.color, R.layout.spinner_item_text);
        color_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color_spinner.setAdapter(color_adapter);

        Spinner retent_spinner = findViewById(R.id.exp_spinner7);
        Switch retent_switch = findViewById(R.id.exp_switch7);
        ArrayAdapter<CharSequence> retent_adapter = ArrayAdapter.createFromResource(this, R.array.retention, R.layout.spinner_item_text);
        retent_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        retent_spinner.setAdapter(retent_adapter);

        Spinner arrang_spinner = findViewById(R.id.exp_spinner8);
        Switch arrang_switch = findViewById(R.id.exp_switch8);
        ArrayAdapter<CharSequence> arrang_adapter = ArrayAdapter.createFromResource(this, R.array.arrangement, R.layout.spinner_item_text);
        arrang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrang_spinner.setAdapter(arrang_adapter);
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
