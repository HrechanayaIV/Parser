package services;

import dao.factory.FactoryDAO;
import model.Article;
import model.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String MAIN_URL = "https://ain.ua/";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String MENU_ATTRIBUTE = "sub-menu";
    //private static final String CATEGORY_VALUE = "menu-item-object-category";
    private static final String POST_LINK_VALUE = "post-link";
    private static final String POST_TITLE_VALUE = "post-title";
    private static final String ID_ATTRIBUTE = "id";
    private static final String ID_VALUE = "post-content";
    private static final String HREF_VALUE = "href";
    private static final String A_TAG = "a";
    private static final String P_TAG = "p";
    private static final String LI_TAG = "li";

    static List<Article> articleList;
    static List<Category>categoryList;

    public List<Article> populateCategory() throws IOException {
        categoryList = new ArrayList<>();
        articleList = new ArrayList<>();
        int articleId = 1;

        Document doc = Jsoup.connect(MAIN_URL).userAgent(USER_AGENT).get();

        Element menuElement = doc.getElementsByAttributeValue(CLASS_ATTRIBUTE, MENU_ATTRIBUTE).first();

        final int[] categoryId = {1};
            Elements categoryNames = menuElement.getElementsByTag(LI_TAG);
            categoryNames.forEach(categoryName->{
                String name = categoryName.text();
                String categoryUrl = categoryName.getElementsByTag(A_TAG).attr(HREF_VALUE);
                Category category = new Category(categoryId[0], name, categoryUrl);
                try {
                    FactoryDAO.getCategoryDAO().create(category);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                categoryList.add(category);
                categoryId[0]++;
            });

        //});
        for (Category category : categoryList) {
            articleList.add(populateArticles(category, articleId));
            articleId++;
        }

        return articleList;
    }
    public Article populateArticles (Category category, int articleId) throws IOException {
        Document doc = Jsoup.connect(category.getCategory_url()).userAgent(USER_AGENT).get();
        //take the first post link. It is the latest article
        Element postUrlElement = doc.getElementsByAttributeValue(CLASS_ATTRIBUTE, POST_LINK_VALUE).first();


        String url = postUrlElement.attr(HREF_VALUE) == null ? postUrlElement.getElementsByAttributeValue(CLASS_ATTRIBUTE, POST_LINK_VALUE).first().attr(HREF_VALUE) : postUrlElement.attr(HREF_VALUE);

        String title = doc.getElementsByAttributeValue(CLASS_ATTRIBUTE,POST_TITLE_VALUE).first().text();
        String text = null;
            try {
            text = getTextFromArticle(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Article article = new Article(articleId, url, title, text, category.getCategory_id());

        return article;
    }

    public String getTextFromArticle(String url) throws IOException {
        StringBuilder text = new StringBuilder();
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();

        Elements postElements = doc.getElementsByAttributeValue(ID_ATTRIBUTE, ID_VALUE);

        postElements.forEach(postElement->{
            String tagText = postElement.text();
            text.append(tagText);
            // Elements pTagText = postElement.getElementsByTag(P_TAG).;
            //pTagText.forEach(tagText-> text.append(tagText.text()));
        });


        return text.toString();
    }

    public static List<Article> getArticleList() {
        return articleList;
    }
}
