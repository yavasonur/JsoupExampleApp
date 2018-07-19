package com.example.onur.jsoupexampleapp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Article implements Serializable{

    private String writer;
    private String header;
    private String photoPath;
    private String articleLink;
    private String content = "";

    public static List<Article> articleList = new ArrayList<>();

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
