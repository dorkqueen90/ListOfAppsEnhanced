package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.uncc.hw04.utils.App;

import static edu.uncc.hw04.AppCategoriesActivity.APP_KEY;

public class AppDetailsActivity extends AppCompatActivity {

    App app;
    TextView appName;
    TextView artistName;
    TextView releaseDate;
    ImageView image;
    ListView listview;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);
        setTitle("App Details");

        appName = findViewById(R.id.appName);
        artistName = findViewById(R.id.artistName);
        releaseDate = findViewById(R.id.releaseDate);
        image = findViewById(R.id.imageView);
        listview = findViewById(R.id.listViewGenres);

        Intent intent = getIntent();
        app = (App) intent.getSerializableExtra(APP_KEY);

        appName.setText(app.name);
        artistName.setText(app.artistName);
        releaseDate.setText(app.releaseDate);

        Picasso.get().load(app.artworkUrl100).into(image);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, app.getGenres());
        listview.setAdapter(adapter);

    }

}
