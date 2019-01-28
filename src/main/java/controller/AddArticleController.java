package controller;

import dao.factory.FactoryDAO;
import model.Article;
import services.Parser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addArticle")
public class AddArticleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addArticle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        try {
            Parser parser = new Parser();
            for (Article article: parser.populateArticles()){
                //add article to db
                FactoryDAO.getArticleDAO().create(article);
            }

            //show some feedback for user
            resp.getWriter().println("Articles added!<br><br>");
            resp.getWriter().println("<br>");
            //show link to see all article
            String link = "<form action=\"article\">\n" +
                    "<p><input type=\"submit\" value=\"ShowAllArticle\"></p>\n" +
                    "</form>";
            resp.getWriter().println(link);

        } catch (Exception e) {
            //show more error for user
            resp.getWriter().println("Error in adding Product");
        }
    }
}
