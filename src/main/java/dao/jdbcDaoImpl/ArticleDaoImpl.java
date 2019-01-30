package dao.jdbcDaoImpl;

import dao.ArticleDAO;
import model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDAO {
    private static final String SAVE = "INSERT INTO parserdb.article (article_id, url, title, article_text,category_id ) VALUES(?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM parserdb.article WHERE article_id = ?";
    private static final String UPDATE = "UPDATE parserdb.article SET url = ?, title = ?, article_text= ? category_id = ? WHERE article_id = ?";
    private static final String DELETE = "DELETE FROM parserdb.article WHERE article_id = ?";
    private static final String FIND_ALL = "SELECT * FROM parserdb.article";

    private Connection connection;

    public ArticleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Article article) {
        try (PreparedStatement preparedStatementSAVE = connection.prepareStatement(SAVE)){
            preparedStatementSAVE.setInt(1,article.getArticle_id());
            preparedStatementSAVE.setString(2, article.getUrl());
            preparedStatementSAVE.setString(3, article.getTitle());
            preparedStatementSAVE.setString(4, article.getArticle_text());
            preparedStatementSAVE.setInt(5, article.getCategory_id());
            preparedStatementSAVE.execute();
        } catch (SQLException e ) {
            System.out.println("Failed to save your article" + article.getTitle() + e);
        }
    }

    @Override
    public Article getById(Integer id) {
        Article article = new Article();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Article> projects = articleBuilder(resultSet);
            if (projects.size() > 0) {
                article = projects.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            //System.out.println("Cant find your article with id = " + id);
        }
        return article;
    }

   @Override
   public void update(Article article) {
       try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
           preparedStatement.setString(1, article.getUrl());
           preparedStatement.setString(2, article.getTitle());
           preparedStatement.setString(3, article.getArticle_text());
           preparedStatement.setInt(4,article.getArticle_id());
           preparedStatement.setInt(5,article.getCategory_id());
           preparedStatement.execute();
       } catch (SQLException e) {
           System.out.println("Cant find your article with id = " + article.getArticle_id() + e);
       }
   }

   @Override
   public void delete(Article article) {
       try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
           preparedStatement.setInt(1, article.getArticle_id());
       } catch (SQLException e) {
           System.out.println("Cant find your article with id + " + article.getArticle_id());
       }
   }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            articles = articleBuilder(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error while trying to find all in your articles :");
            e.printStackTrace();
        }
        return articles;
    }

    private List<Article> articleBuilder(ResultSet resultSet) throws SQLException {
        List<Article> articles = new ArrayList<>();
        Integer article_id;
        String url;
        String title;
        String article_text;
        Integer category_id;

        while (resultSet.next()) {
            article_id = resultSet.getInt("article_id");
            url = resultSet.getString("url");
            title = resultSet.getString("title");
            article_text = resultSet.getString("article_text");
            category_id = resultSet.getInt("category_id");
            articles.add(new Article(article_id, url, title, article_text, category_id));
        }
        return articles;
    }
}
