package com.example.alextagocsfair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity{
    List<String> plant_names = new ArrayList<String>();
    List<String> plant_links = new ArrayList<String>();
    List<String> image_urls = new ArrayList<String>();
    String url = "https://www.wildflower.org/collections/collection.php?collection=";
    String location;
    int count = 0;
    TextView tv7;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        tv7 = findViewById(R.id.textView7);
        Button button4 = findViewById(R.id.button4);

        location = getIntent().getStringExtra("location");
        url = "https://www.wildflower.org/collections/collection.php?collection=" + location + "&pagecount=200";


        recyclerView = findViewById(R.id.recyclerView);

        webscrape wb = new webscrape();

        button4.setOnClickListener(view -> {
            Intent j = new Intent(ThirdActivity.this, SecondActivity.class);
            startActivity(j);
        });
        tv7.setText("Native Plants in: " + location);

        wb.execute();

    }

    private class webscrape extends AsyncTask<Void, Void, Void>{
        String text = "";
        int i = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc = Jsoup.connect(url).get();
                Element table = doc.select("table").get(0);
                Elements rows = table.select("tr");

                for(int i = 1; i < rows.size() - 1; i++){
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String plant_name = cols.select("i").text();
                    String plant_link = "https://www.wildflower.org" +
                            cols.select("a")
                            .attr("href")
                            .substring(2);
                    String image_url =
                            cols.select("img")
                            .attr("src");
                    if(image_url.equals("")){
                        image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png";
                    }else{
                        image_url = "https://www.wildflower.org" + image_url;
                    }

                    plant_names.add(plant_name);
                    plant_links.add(plant_link);
                    image_urls.add(image_url);

                    text += (i) + ". " + plant_names.get(i-1) + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            MyAdapter myAdapter = new MyAdapter(ThirdActivity.this, plant_names, plant_links, image_urls);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ThirdActivity.this));
//                tv2 = findViewById(R.id.textView2);
//                tv2.setText(text);
        }
    }

}






