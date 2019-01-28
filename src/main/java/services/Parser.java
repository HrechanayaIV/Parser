package services;

import model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Parser {
    private static final String URL = "https://ain.ua/";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String ID_ATTRIBUTE = "id";
    private static final String ATTR_CLASS_VALUE = "block-interesting-small-item interesting-item";
    private static final String ATTR_ID_VALUE = "post-content";
    private static final String ATTR_HREF = "href";
    static List<Article> articleList;

    public List<Article> populateArticles () throws IOException {
        articleList = new ArrayList<>();

        Document doc = Jsoup.connect(URL).userAgent(USER_AGENT).get();

        Elements ulElements = doc.getElementsByAttributeValue(CLASS_ATTRIBUTE, ATTR_CLASS_VALUE);

        AtomicInteger id = new AtomicInteger(1);
        ulElements.forEach(ulElement->{
            String title = ulElement.child(0).text();
            String url = ulElement.child(0).child(0).attr(ATTR_HREF);
            String text = null;
            try {
                text = getTextFromArticle(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            articleList.add(new Article(id,url,title,text));
            id.getAndIncrement();
        });

        //articleList.get(1).setText(getTextFromArticle(articleList.get(1).getUrl()));

        //System.out.println(articleList.get(1));
        return articleList;
    }

    public String getTextFromArticle(String url) throws IOException {
        StringBuilder text = new StringBuilder();
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();

        Elements postElements = doc.getElementsByAttributeValue(ID_ATTRIBUTE, ATTR_ID_VALUE);

        postElements.forEach(postElement->{
            Elements pTagText = postElement.getElementsByTag("p");
            pTagText.forEach(tagText-> text.append(tagText.text()));
        });


        return text.toString();
    }
}
