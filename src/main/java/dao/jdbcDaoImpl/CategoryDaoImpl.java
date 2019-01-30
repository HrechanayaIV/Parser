package dao.jdbcDaoImpl;

import dao.CategoryDAO;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDAO {
    private static final String SAVE = "INSERT INTO parserdb.category (category_id, category_name, category_url ) VALUES(?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM parserdb.category WHERE category_id = ?";
    private static final String UPDATE = "UPDATE parserdb.category SET category_name = ?, category_url = ? WHERE category_id = ?";
    private static final String DELETE = "DELETE FROM parserdb.category WHERE category_id = ?";
    private static final String FIND_ALL = "SELECT * FROM parserdb.category";

    private Connection connection;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Category category) {
        try (PreparedStatement preparedStatementSAVE = connection.prepareStatement(SAVE)){
            preparedStatementSAVE.setInt(1,category.getCategory_id());
            preparedStatementSAVE.setString(2, category.getCategory_name());
            preparedStatementSAVE.setString(3, category.getCategory_url());
            preparedStatementSAVE.execute();
        } catch (SQLException e ) {
            System.out.println("Failed to save your category " + e);
        }
    }

    @Override
    public Category getById(Integer id) {
        Category category = new Category();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categories = categoryBuilder(resultSet);
            if (categories.size() > 0) {
                category = categories.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Cant find your category" + e);
        }
        return category;
    }

    @Override
    public void update(Category category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, category.getCategory_name());
            preparedStatement.setString(2, category.getCategory_url());
            preparedStatement.setInt(3,category.getCategory_id());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Cant find your category with " + e);
        }

    }
    //TODO need to finish
    @Override
    public void delete(Category category) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, category.getCategory_id());
        } catch (SQLException e) {
            System.out.println("Cant find your category " + e);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            categories = categoryBuilder(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error while trying to find all in your category :");
            e.printStackTrace();
        }
        return categories;
    }

    private List<Category> categoryBuilder(ResultSet resultSet) throws SQLException {
        List<Category> categories = new ArrayList<>();
        Integer category_id;
        String category_name;
        String category_url;

        while (resultSet.next()) {
            category_id = resultSet.getInt("category_id");
            category_name = resultSet.getString("category_name");
            category_url = resultSet.getString("category_url");
            categories.add(new Category(category_id, category_name, category_url));
        }
        return categories;
    }
}
