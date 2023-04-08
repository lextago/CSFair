package com.example.alextagocsfair;

import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity{
    List<String> plant_names = new ArrayList<>();
    List<String> plant_links = new ArrayList<>();
    List<String> image_urls = new ArrayList<>();
    List<String> common_names = new ArrayList<>();
    String url = "https://www.wildflower.org/collections/collection.php?collection=";
    String location;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        location = getIntent().getStringExtra("location");
        url = "https://www.wildflower.org/collections/collection.php?collection=" + location + "&pagecount=200";

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

                    plant_names.add(plant_name);
                    plant_links.add(plant_link);
                    image_urls.add(image_url);
                    common_names.add(common_name);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            MyAdapter myAdapter = new MyAdapter(ThirdActivity.this, plant_names, plant_links, image_urls,common_names, location);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ThirdActivity.this));
        }
    }

}






