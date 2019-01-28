package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Article {
    private Long article_id;
    private String url;
    private String title;
    private String text;

    public Article(AtomicInteger id, String url, String title, String text) {
        //this.article_id = id;
        this.url = url;
        this.title = title;
        this.text = text;
    }

    public Article() {
    }

    public Article(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public Article(Long article_id, String url, String title, String text) {
        this.article_id = article_id;
        this.url = url;
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return article_id;
    }
    public void setId(Long id) { this.article_id = id; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Article{\n" +
                "url='" + url + '\n' +
                ", title='" + title + '\n' +
                ", text = " + text + '\n' +
                '}';
    }
}
