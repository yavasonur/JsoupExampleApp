package com.example.onur.jsoupexampleapp.AsyncTask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;

import com.example.onur.jsoupexampleapp.Activities.MainActivity;
import com.example.onur.jsoupexampleapp.Adapters.RvAdapter;
import com.example.onur.jsoupexampleapp.Model.Article;
import com.example.onur.jsoupexampleapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dmax.dialog.SpotsDialog;

public class AsyncGetArticles extends AsyncTask<Void,Void,Void> {

    private Context context;
    private static final String url = "https://www.wired.com/most-recent/";

    String sWriter, sTitle, sPhoto, sLink;

    private AlertDialog progressDialog;

    public AsyncGetArticles(Context mContext){
        this.context = mContext;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Article.articleList.clear();

        try{

            Document doc = Jsoup.connect(url).get();

            Elements writer = doc.select("a[tabindex=\"-1\"]");
            Elements title = doc.select("a[class=\"archive-item-component__link\"]");
            Elements photo = doc.select("img");

            //for only 10 article
            for(int i=0; i<10; i++){

                Article article = new Article();

                sWriter = writer.get(i).text();
                sTitle = title.get((i * 2) + 1).text();
                sPhoto = photo.get(i).attr("src");
                sLink = "https://wired.com" + title.get((i * 2) + 1).attr("href");

                article.setWriter(sWriter);
                article.setHeader(sTitle);
                article.setArticleLink(sLink);
                article.setPhotoPath(sPhoto);

                Article.articleList.add(article);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new SpotsDialog(context, R.style.Custom);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        RvAdapter myAdapter = new RvAdapter(context,Article.articleList);
        MainActivity.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MainActivity.recyclerView.setAdapter(myAdapter);
    }
}
