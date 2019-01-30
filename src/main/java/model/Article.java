package model;


public class Article {
    private Integer article_id;
    private String url;
    private String title;
    private String article_text;
    private Integer category_id;

    public Article() {
    }

    public Article(Integer article_id, String url, String title, String article_text, Integer category_id) {
        this.article_id = article_id;
        this.url = url;
        this.title = title;
        this.article_text = article_text;
        this.category_id = category_id;
    }

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

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer categoryId) {
        this.category_id = categoryId;
    }
}
