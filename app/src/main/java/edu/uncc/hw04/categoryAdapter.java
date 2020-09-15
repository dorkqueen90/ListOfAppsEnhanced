package edu.uncc.hw04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.uncc.hw04.utils.App;

import static edu.uncc.hw04.AppCategoriesActivity.APP_KEY;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {

    ArrayList<App> data;

    public categoryAdapter(ArrayList<App> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        App app;
        TextView appName;
        TextView artistName;
        TextView releaseDate;
        ImageView image;
        Context context;

        public void setUp(App app){
            this.app = app;
            this.appName.setText(app.name);
            this.artistName.setText(app.artistName);
            this.releaseDate.setText(app.releaseDate);
            Picasso.get().load(app.artworkUrl100).into(image);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.nameId);
            artistName = itemView.findViewById(R.id.artistId);
            releaseDate = itemView.findViewById(R.id.releaseId);
            image = itemView.findViewById(R.id.imageIcon);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo", "clicked" + app);
                    Intent intent = new Intent(context, AppDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(APP_KEY, app);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        App app = data.get(position);
        holder.setUp(app);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}