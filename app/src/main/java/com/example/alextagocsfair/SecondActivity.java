package com.example.alextagocsfair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    Boolean isLocation;
    String location;

    private static final String[] locations = new String[] {
            "Select Location:",
            "AL", "AK", "AZ", "AR", "CA_North",
            "CA_South", "CO", "CT","DC", "DE",
            "FL", "FL_North", "FL_Central", "FL_South", "GA",
            "HI", "ID", "IL", "IN", "IA",
            "KS", "KY", "LA", "ME", "MD",
            "MA", "MI", "MN", "MS", "MO",
            "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH",
            "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX_East", "TX_Central", "TX_NorthCentral",
            "TX_West", "TX_HighPlains", "TX_South", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        TextView tv4 = findViewById(R.id.textView4);
        Button button3 = findViewById(R.id.button3);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, locations);
        Spinner spinner = findViewById(R.id.spinner1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position > 0){
                    isLocation = true;
                    location = locations[position];
                    Toast.makeText(SecondActivity.this, "Location: "+location, Toast.LENGTH_SHORT).show();
                }else{
                    isLocation = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button3.setOnClickListener(view ->{
            if(isLocation){
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("location", location);
                startActivity(i);
            }else{
                Toast.makeText(SecondActivity.this, "Enter a valid location", Toast.LENGTH_SHORT).show();
            }
        });



        };
    }