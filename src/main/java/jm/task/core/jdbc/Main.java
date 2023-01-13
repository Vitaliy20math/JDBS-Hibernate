package jm.task.core.jdbc;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
        //userDaoJDBC.dropUsersTable();
        userDaoJDBC.saveUser("Ann", "Hattway", (byte) 44);
        //userDaoJDBC.removeUserById(2);
        //System.out.println(userDaoJDBC.getAllUsers());
        //userDaoJDBC.cleanUsersTable();
    }
}
