package model;

import java.util.List;
import java.util.Objects;

public class Category {
    private Integer category_id;
    private String category_name;
    private String category_url;
    private List<Article> articles;

    public Category() {
    }

    public Category(Integer category_id, String category_name, String category_url) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_url = category_url;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_url() {
        return category_url;
    }

    public void setCategory_url(String category_url) {
        this.category_url = category_url;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(category_id, category.category_id) &&
                Objects.equals(category_name, category.category_name) &&
                Objects.equals(category_url, category.category_url) &&
                Objects.equals(articles, category.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, category_name, category_url, articles);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", category_url='" + category_url + '\'' +
                ", articles=" + articles +
                '}';
    }
}
