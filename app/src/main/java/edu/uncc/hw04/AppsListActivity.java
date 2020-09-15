package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.hw04.utils.App;
import edu.uncc.hw04.utils.Data;

import static edu.uncc.hw04.AppCategoriesActivity.APP_KEY;

public class AppsListActivity extends AppCompatActivity {

    ArrayList<String> categories = new ArrayList<>(Data.apps.keySet());
    ArrayList<App> app;
    ArrayList<App> filteredApp = new ArrayList<App>();
    int appPosition;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;
    EditText filterText;
    ImageButton filterButton;
    ImageButton ascending;
    ImageButton descending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        if(getIntent() != null && getIntent().getExtras() != null){
            appPosition = getIntent().getIntExtra(APP_KEY, 0);
        }
        app = Data.apps.get(categories.get(appPosition));

        String titleName = categories.get(appPosition);
        setTitle(titleName);

        recyclerView = findViewById(R.id.rvItems);
        recyclerView.setHasFixedSize(true);
        layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        filterText = findViewById(R.id.filterText);
        filterButton = findViewById(R.id.filterButton);
        ascending = findViewById(R.id.ascendingButton);
        descending = findViewById(R.id.descendingButton);

        adapter = new categoryAdapter(app);
        recyclerView.setAdapter(adapter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filterText.getText().toString().isEmpty()){
                    if(filteredApp.isEmpty()) {
                        filteredApp = app;
                        return;
                    } else{
                        for(App b : filteredApp) {
                            filteredApp.remove(b);
                        }
                    }
                }
                for(App a : app){
                     if(a.name.toLowerCase().contains(filterText.getText().toString())){
                        filteredApp.add(a);
                    }
                }
                adapter = new categoryAdapter(filteredApp);
                recyclerView.setAdapter(adapter);
            }
        });
        descending.setOnClickListener(new View.OnClickListener() {
            @Override //latest release date first
            public void onClick(View v) {
                Log.d("demo", "descending button clicked");
                    Collections.sort(app, new Comparator<App>() {
                        @Override
                        public int compare(App o1, App o2) {
                            if(o1.getReleaseDate() == null || o2.getReleaseDate() == null) {
                                return 0;
                            } else {
                                return o2.getReleaseDate().compareTo(o1.getReleaseDate());
                            }
                        }
                    });
                adapter = new categoryAdapter(app);
                recyclerView.setAdapter(adapter);
            }
        });
        ascending.setOnClickListener(new View.OnClickListener() {
            @Override //newest release date last
            public void onClick(View v) {
                Collections.sort(app, new Comparator<App>() {
                    @Override
                    public int compare(App o1, App o2) {
                        if(o1.getReleaseDate() == null || o2.getReleaseDate() == null) {
                            return 0;
                        } else {
                            return o1.getReleaseDate().compareTo(o2.getReleaseDate());
                        }
                    }
                });
                adapter = new categoryAdapter(app);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
