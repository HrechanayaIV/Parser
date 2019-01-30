package controller;

import dao.factory.FactoryDAO;
import model.Article;
import services.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddArticleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        try {
            Parser parser = new Parser();
            //parser.populateCategory();
            for (Article article: parser.populateCategory()){
                //add article to db
                FactoryDAO.getArticleDAO().create(article);
            }
        } catch (Exception e) {
            //show more error for user
            req.getRequestDispatcher("/error.jsp").forward(req,resp);
        }

        req.getRequestDispatcher("/addArticle.jsp").forward(req, resp);
    }
}
