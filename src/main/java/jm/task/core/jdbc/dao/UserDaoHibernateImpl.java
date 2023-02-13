package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String createUsersTableCommand = "CREATE TABLE IF NOT EXISTS USERS \n" +
                    "(\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    lastName VARCHAR(30) NOT NULL,\n" +
                    "    age int NOT NULL\n" +
                    "    \n" +
                    ")";
            session.createSQLQuery(createUsersTableCommand).executeUpdate();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String dropUsers = "drop table USERS";
            session.createSQLQuery(dropUsers).executeUpdate();
            transaction.commit();
            Util.getConnection().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user;
        try (Session session = Util.getSessionFactory().openSession()) {

            user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            Util.getConnection().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction transaction = null;
        User user;

        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            user = (User)session.load(User.class,id);
            session.delete(user);
            transaction.commit();
            Util.getConnection().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {

        Transaction transaction = null;
        List<User> list = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String getAllUsers = "SELECT * FROM users";
            list = (List<User>)session.createSQLQuery(getAllUsers).addEntity(User.class).list();
            transaction.commit();
            Util.getConnection().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {

        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            String cleanUsers = "truncate table users";
            session.createSQLQuery(cleanUsers).executeUpdate();
            transaction.commit();
            Util.getConnection().close();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
