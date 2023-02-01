package jm.task.core.jdbc.dao;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = null;
    @Override
    public void createUsersTable() {

        connection = Util.getConnection();

        String createTableCommand = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, nameUser VARCHAR(10), lastNameUser VARCHAR(22), ageUser TINYINT(3), PRIMARY KEY(id))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableCommand)) {
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {

        connection = Util.getConnection();

        String dropTableCommand = "DROP TABLE IF EXISTS users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(dropTableCommand)) {
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {

        connection = Util.getConnection();

        String insertUsersCommand = "INSERT INTO users (nameUser, lastNameUser, ageUser) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUsersCommand);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {

        connection = Util.getConnection();

        String removeById = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(removeById);) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {

        connection = Util.getConnection();

        String query = "SELECT * FROM users";

        List<User> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                list.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {

        connection = Util.getConnection();

        String truncateTable = "TRUNCATE TABLE users";

        try (PreparedStatement preparedStatement = connection.prepareStatement(truncateTable)) {
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}





