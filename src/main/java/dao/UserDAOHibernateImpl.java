package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/*
Реализация на Hibernate, синглтон. Настройки из DBHelper.
 */
@Repository
public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOHibernateImpl(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
    }

    //взять всех юзеров
    @Override
    public List<User> getAllUsers() throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }
    //добавить юзера
    @Override
    public void addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
    //обновить юзера
    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }
    //взять юзера по id
    @Override
    public User getUserById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :paramId");
        List<User> userList = query.setParameter("paramId", id).list();
        User user = null;
        for (User u : userList) {
            if (u.getId() == id) {
                user = u;
            }
        }
        transaction.commit();
        session.close();
        return user;
    }
    //удалить юзера
    @Override
    public boolean deleteUser(long id) throws SQLException {
        boolean rowDelete;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete User where id = :paramId");
        query.setParameter("paramId", id);
        rowDelete = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return rowDelete;
    }

    //достаем юзера по имени и фамилии (логин пароль)
    @Override
    public User getUserByNameAndSurname(String name, String surname) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name = :paramName");
        query.setParameter("paramName", name);
        List<User> users = query.list();
        User userLogin = null;
        for (User u : users) {
            if (u.getName().equals(name) && u.getSurname().equals(surname)) {
                userLogin = u;
            }
        }
        transaction.commit();
        session.close();
        return userLogin;
    }
}