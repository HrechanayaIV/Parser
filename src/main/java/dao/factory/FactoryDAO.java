package dao.factory;

import connection.DbConnection;
import dao.ArticleDAO;
import dao.jdbcDaoImpl.ArticleDaoImpl;

import java.sql.SQLException;

public class FactoryDAO {
    private static ArticleDAO articleDAO;
    public static ArticleDAO getArticleDAO() throws SQLException {
        if(articleDAO == null){
            articleDAO = new ArticleDaoImpl(DbConnection.getConnection());
        }
        return articleDAO;
    }

}
