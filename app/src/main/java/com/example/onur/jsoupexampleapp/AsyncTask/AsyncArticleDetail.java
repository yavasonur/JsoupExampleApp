package com.example.onur.jsoupexampleapp.AsyncTask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.onur.jsoupexampleapp.Activities.ArticleDetail;
import com.example.onur.jsoupexampleapp.Model.Article;
import com.example.onur.jsoupexampleapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.Serializable;

import dmax.dialog.SpotsDialog;

public class AsyncArticleDetail extends AsyncTask<String,Void,Void> {

    private Context context;
    private Article article;
    AlertDialog progressDialog;

    String articleContent = "";

    public AsyncArticleDetail(Context context, Article article){
        this.context = context;
        this.article = article;
    }


    @Override
    protected Void doInBackground(String... url) {

        try{

            Document doc = Jsoup.connect(url[0]).get();
            articleContent = "";
            Elements content = doc.getElementsByTag("p");
            for (int i=0; i<content.size(); i++){
                if(content.get(i).attr("class") == ""){
                    articleContent += content.get(i).text();
                }
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
        article.setContent(articleContent);
        progressDialog.dismiss();

        Intent i = new Intent(context, ArticleDetail.class);
        i.putExtra("articleObj", (Serializable) article);
        context.startActivity(i);

    }
}
