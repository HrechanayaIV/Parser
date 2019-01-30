package controller;

import dao.factory.FactoryDAO;
import model.Article;
import services.WordCountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CounterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String word = req.getParameter("word");
        String id = req.getParameter("id");
        Integer quantity = 0;
        try {
            quantity = getQuantity(FactoryDAO.getArticleDAO().getById(Integer.valueOf(id)), word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("word", word);
        req.setAttribute("quantity", quantity);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/resultCounter.jsp");

        dispatcher.forward(req, resp);

    }

    private Integer getQuantity(Article article, String word) {
        String text;
        Integer quantity = 0;
        text = article.getArticle_text();
        WordCountService counter = new WordCountService();
        Map<String, Integer> wordsMap = counter.getFrequencyMap(counter.getWords(text));
        quantity += wordsMap.get(word) == null ? 0 : wordsMap.get(word);

        return quantity;
    }
}
