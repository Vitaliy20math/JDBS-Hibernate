package jm.task.core.jdbc;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Ann", "afdhdfh", (byte) 44);
        userDaoHibernate.saveUser("Ann", "afdhdfh", (byte) 44);
        userDaoHibernate.saveUser("Ann", "afdhdfh", (byte) 44);
        userDaoHibernate.saveUser("Ann", "afdhdfh", (byte) 44);
        for (User user : userDaoHibernate.getAllUsers()) {
            System.out.println(user);
        }
        //userDaoHibernate.cleanUsersTable();
        //userDaoHibernate.removeUserById(2);
        //userDaoHibernate.dropUsersTable();


    }
}
