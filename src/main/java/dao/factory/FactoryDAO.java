package dao.factory;

import connection.DbConnection;
import dao.ArticleDAO;
import dao.CategoryDAO;
import dao.jdbcDaoImpl.ArticleDaoImpl;
import dao.jdbcDaoImpl.CategoryDaoImpl;

import java.sql.SQLException;

public class FactoryDAO {
    private static ArticleDAO articleDAO;
    private static CategoryDAO categoryDAO;
    public static ArticleDAO getArticleDAO() throws SQLException {
        if(articleDAO == null){
            articleDAO = new ArticleDaoImpl(DbConnection.getConnection());
        }
        return articleDAO;
    }
    public static CategoryDAO getCategoryDAO() throws SQLException {
        if(categoryDAO == null){
            categoryDAO = new CategoryDaoImpl(DbConnection.getConnection());
        }
        return categoryDAO;
    }

}
