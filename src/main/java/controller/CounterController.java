package controller;

import dao.factory.FactoryDAO;
import model.Article;
import services.WordCountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/resultCounter")
public class CounterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String word = req.getParameter("word");

        String text;
        Integer quantity = 0;
        try {
            for (Article article: FactoryDAO.getArticleDAO().findAll()) {
                text = article.getText();
                WordCountService counter = new WordCountService(text,word);
                Map<String,Integer> wordsMap = counter.getFrequencyMap(counter.getWords(text));
                quantity += wordsMap.get(word);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher request = req.getRequestDispatcher("resultCounter.jsp");
            request.forward(req, resp);

    }
}
