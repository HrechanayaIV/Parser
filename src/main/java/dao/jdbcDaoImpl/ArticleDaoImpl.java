package dao.jdbcDaoImpl;

import dao.ArticleDAO;
import model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDAO {
    private static final String SAVE = "INSERT INTO article (article_text, url, title, article_text) VALUES(?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM article WHERE id = ?";
    private static final String UPDATE = "UPDATE article SET url = ?, title = ?, article_text= ? WHERE article_id = ?";
    private static final String DELETE = "DELETE FROM article WHERE article_id = ?";
    private static final String FIND_ALL = "SELECT * FROM article";
    private static final String GET_LAST_ID = "SELECT LAST_INSERT_ID()";
    //private static final String FIND_BY_TITLE = "SELECT * FROM article WHERE title LIKE ?";

    private Connection connection;

    public ArticleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Article article) {
        //Long id = null;
        try (PreparedStatement preparedStatementSAVE = connection.prepareStatement(SAVE);
             PreparedStatement preparedStatementLastID = connection.prepareStatement(GET_LAST_ID)) {
            preparedStatementSAVE.setLong(1,article.getId());
            preparedStatementSAVE.setString(2, article.getUrl());
            preparedStatementSAVE.setString(3, article.getTitle());
            preparedStatementSAVE.setString(4, article.getText());
            preparedStatementSAVE.execute();
            ResultSet resultSet = preparedStatementLastID.executeQuery();
            resultSet.next();
            //id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            //System.out.println("Failed to save your project" + article.getTitle() + e);
        }
        //return id;
    }

    @Override
    public Article getById(Long id) {
        Article article = new Article();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
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
           preparedStatement.setString(3, article.getText());
           preparedStatement.setLong(4,article.getId());
           preparedStatement.execute();
       } catch (SQLException e) {
           System.out.println("Cant find your article with id = " + article.getId() + e);
       }
   }

   @Override
   public void delete(Article article) {
       try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
           preparedStatement.setLong(1, article.getId());
       } catch (SQLException e) {
           System.out.println("Cant find your article with id + " + article.getId());
       }
   }

    @Override
    public List<Article> findAll() {
        List<Article> projects = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            projects = articleBuilder(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error while trying to find all in your articles :");
            e.printStackTrace();
        }
        return projects;
    }

    private List<Article> articleBuilder(ResultSet resultSet) throws SQLException {
        List<Article> articles = new ArrayList<>();
        //TODO: remove ID?
        long article_id;
        String url;
        String title;
        String article_text;

        while (resultSet.next()) {
            article_id = resultSet.getLong("article_id");
            url = resultSet.getString("url");
            title = resultSet.getString("title");
            article_text = resultSet.getString("article_text");
            articles.add(new Article(article_id, url, title, article_text));
        }
        return articles;
    }
}
