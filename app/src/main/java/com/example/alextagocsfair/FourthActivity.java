package com.example.alextagocsfair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FourthActivity extends AppCompatActivity {
    String location, plant_name, plant_link, image_url, common_name, subtitle1_text, subtitle3_text;
    ImageView imageView;
    TextView title, description, subtitle1, subtitle2, subtitle3;
    ProgressBar progressBar;

    String description_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_layout);
        imageView = findViewById(R.id.imageView2);
        title = findViewById(R.id.textView6);
        Toolbar toolbar = findViewById(R.id.toolbar);
        description = findViewById(R.id.textView8);
        subtitle1 = findViewById(R.id.textView11);
        subtitle2 = findViewById(R.id.textView10);
        subtitle3 = findViewById(R.id.textView12);
        progressBar = findViewById(R.id.progressBar2);

        plant_name = getIntent().getStringExtra("plant_name");
        plant_link = getIntent().getStringExtra("plant_link");
        image_url = getIntent().getStringExtra("image_url");
        location = getIntent().getStringExtra("location");
        common_name = getIntent().getStringExtra("common_name");

        title.setText(plant_name);
        Picasso.with(this)
                .load(image_url)
//                .resize(300,300)
                .fit()
                .centerInside()
                .into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(plant_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webscrape wb = new webscrape();
        wb.execute();

//        common_name.substring(0,common_name.length()-2);
        while(common_name.contains("\n")){
            int index = common_name.indexOf("\n");
            common_name =common_name.substring(0,index) + ", " + common_name.substring(index+1);
        }
        common_name = "Common names: " + common_name.substring(0, common_name.length()-2);


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

    private class webscrape extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            description.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            title.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            subtitle1.setVisibility(View.INVISIBLE);
            subtitle2.setVisibility(View.INVISIBLE);
            subtitle3.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(plant_link).get();
                Element page = doc.select("div#fullpage_content").get(0);
                Element paragraph = page.select("p").get(0);
                description_text = paragraph.text();

                subtitle1_text = "Full name: " + page.select("h3").get(0).text();
                subtitle3_text = "Family: " + page.select("h3").get(2).text();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            progressBar.setVisibility(View.INVISIBLE);
            description.setText(description_text);
            subtitle2.setText(common_name);
            subtitle1.setText(subtitle1_text);
            subtitle3.setText(subtitle3_text);
            description.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            subtitle1.setVisibility(View.VISIBLE);
            subtitle2.setVisibility(View.VISIBLE);
            subtitle3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
