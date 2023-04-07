package com.example.alextagocsfair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ThirdActivity extends AppCompatActivity{
    String url = "https://www.wildflower.org/collections/collection.php?collection=";
    String location;
    int count = 0;
    TextView tv2;
    TextView tv3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        Button button2 = findViewById(R.id.button2);


        Intent intent = getIntent();
        location = getIntent().getStringExtra("location");

        webscrape wb = new webscrape();

        button2.setOnClickListener(view -> {
            Intent j = new Intent(ThirdActivity.this, SecondActivity.class);
            startActivity(j);
        });

        tv3.setText("Native Plants in: " + location);
        url = "https://www.wildflower.org/collections/collection.php?collection=" + location + "&pagecount=200";
        wb.execute();


    }

    private class webscrape extends AsyncTask<Void, Void, Void>{
        String text = "";
        int i = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc = Jsoup.connect(url).get();
                Elements plant_names = doc.select("i");
                for (Element p: plant_names) {
                    i++;
                    text += i + ". " + p.text() + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
                tv2 = findViewById(R.id.textView2);
                tv2.setText(text);
        }
    }
}






