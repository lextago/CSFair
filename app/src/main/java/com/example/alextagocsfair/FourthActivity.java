package com.example.alextagocsfair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FourthActivity extends AppCompatActivity {
    String description_text = "";
    String location;
    String plant_name;
    String plant_link;
    String image_url;
    ImageView imageView;
    TextView title;
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_layout);
        imageView = findViewById(R.id.imageView2);
        title = findViewById(R.id.textView6);
        Toolbar toolbar = findViewById(R.id.toolbar);
        description = findViewById(R.id.textView8);

        plant_name = getIntent().getStringExtra("plant_name");
        plant_link = getIntent().getStringExtra("plant_link");
        image_url = getIntent().getStringExtra("image_url");
        location = getIntent().getStringExtra("location");

        title.setText(plant_name);
        Picasso.with(this)
                .load(image_url)
//                .resize(300,300)
                .fit()
                .centerInside()
                .into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Plant");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webscrape wb = new webscrape();
        wb.execute();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(this, ThirdActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    private class webscrape extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(plant_link).get();
                Element page = doc.select("div#fullpage_content").get(0);
                Element paragraph = page.select("p").get(0);
                description_text = paragraph.text();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            description.setText(description_text);
        }
    }




}
