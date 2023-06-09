package com.example.alextagocsfair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity{
    List<Plant> plants = new ArrayList<>();
    String url = "https://www.wildflower.org/collections/collection.php?collection=";
    String location;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        location = getIntent().getStringExtra("location");
        url = "https://www.wildflower.org/collections/collection.php?collection=" + location + "&pagecount=200";

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Native Plants in " + location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webscrape wb = new webscrape();

        wb.execute();

    }

    private class webscrape extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

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

                    Element second_col = cols.get(1);
                    String common_name = "";

                    List<TextNode> text_nodes = second_col.textNodes();
                    for(TextNode tn : text_nodes){
                        common_name += tn.text() + "\n";
                    }

                    if(image_url.equals("")){
                        image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png";
                    }else{
                        image_url = "https://www.wildflower.org" + image_url;
                    }

                    Plant new_plant = new Plant(plant_name, plant_link, image_url, common_name);

                    plants.add(new_plant);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            progressBar.setVisibility(View.INVISIBLE);
            MyAdapter myAdapter = new MyAdapter(ThirdActivity.this, location, plants);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ThirdActivity.this));
        }
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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}






