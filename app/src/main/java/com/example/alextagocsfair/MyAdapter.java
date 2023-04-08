package com.example.alextagocsfair;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    List<String> plant_names, plant_links, image_urls, common_names;
    String location;

    public MyAdapter(Context context, List<String> plant_names, List<String> plant_links, List<String> image_urls, List<String> common_names,String location){
        this.context = context;
        this.plant_names = plant_names;
        this.plant_links = plant_links;
        this.image_urls = image_urls;
        this.common_names = common_names;
        this.location = location;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String plant_name = plant_names.get(position);
        String plant_link = plant_links.get(position);
        String image_url = image_urls.get(position);
        String common_name = common_names.get(position);

        holder.plant_name.setText(plant_name);
        holder.common_name.setText(common_name);

        Picasso.with(context)
                .load(image_url)
                .resize(110,110)
                .centerCrop()
                .into(holder.image);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FourthActivity.class);
                intent.putExtra("plant_name", plant_name);
                intent.putExtra("plant_link", plant_link);
                intent.putExtra("image_url", image_url);
                intent.putExtra("location", location);
                intent.putExtra("common_name", common_name);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plant_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView plant_name, common_name;
        ImageView image;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            plant_name = itemView.findViewById(R.id.textView5);
            common_name = itemView.findViewById(R.id.textView7);
            image = itemView.findViewById(R.id.imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
