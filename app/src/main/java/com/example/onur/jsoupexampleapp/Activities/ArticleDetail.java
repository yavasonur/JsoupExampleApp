package com.example.onur.jsoupexampleapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onur.jsoupexampleapp.Model.Article;
import com.example.onur.jsoupexampleapp.R;

public class ArticleDetail extends Activity {

    ImageView ımageView;
    TextView textView,textView2;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ımageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.txtContent);
        textView2 = (TextView)findViewById(R.id.txtHeader);


        article = (Article)getIntent().getSerializableExtra("articleObj");

        getActionBar().setTitle("Article Detail Page");

        textView.setText(article.getContent().toString());
        textView2.setText(article.getHeader().toString());

        Glide.with(this).load(article.getPhotoPath()).into(ımageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.btnShare :
                shareLink();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareLink(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = article.getArticleLink();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Article Link");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Article Share"));
    }
}
