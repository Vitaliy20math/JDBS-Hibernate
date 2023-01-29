package jm.task.core.jdbc;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Ann", "WEr", (byte) 44);
        userDaoJDBC.saveUser("Ben", "Hattway", (byte) 34);
        userDaoJDBC.saveUser("Anns", "HHtway", (byte) 24);
        userDaoJDBC.saveUser("Rn", "tway", (byte) 14);
        //userDaoJDBC.removeUserById(2);
        for (User user : userDaoJDBC.getAllUsers()) {
            System.out.println(user);
        }
        //userDaoJDBC.cleanUsersTable();
        //userDaoJDBC.dropUsersTable();

    }
}
