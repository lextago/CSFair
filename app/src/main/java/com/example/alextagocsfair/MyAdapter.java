package com.example.alextagocsfair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    List<String> plant_names, plant_links, image_urls;

    public MyAdapter(Context context, List<String> plant_names, List<String> plant_links, List<String> image_urls){
        this.context = context;
        this.plant_names = plant_names;
        this.plant_links = plant_links;
        this.image_urls = image_urls;
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
        holder.name.setText(plant_names.get(position));
        Picasso.with(context)
                .load(image_urls.get(position))
                .resize(110,110)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return plant_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView5);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
