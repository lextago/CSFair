package com.example.alextagocsfair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    String url = "https://www.wildflower.org/collections/collection.php?start=0&collection=fl&pagecount=500";
    TextView tv2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);

        webscrape wb = new webscrape();
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






