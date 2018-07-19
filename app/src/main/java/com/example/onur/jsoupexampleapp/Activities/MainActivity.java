package com.example.onur.jsoupexampleapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.onur.jsoupexampleapp.AsyncTask.AsyncGetArticles;
import com.example.onur.jsoupexampleapp.R;

public class MainActivity extends Activity {

    public static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        new AsyncGetArticles(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.refresh :
                new AsyncGetArticles(this).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
