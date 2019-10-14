package com.khilkevichigor.springmvc.dao;

import com.khilkevichigor.springmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
Реализация на Hibernate, синглтон. Настройки из DBHelper.
 */
@Repository //класс который является DAO
public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    //взять всех юзеров
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User").list();
        return allUsers;
    }

    @Override
    //добавить юзера
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    //обновить юзера
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    //взять юзера по id
    public User getUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    @Override
    //удалить юзера
    public void deleteUser(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }

//    @Override
//    //достаем юзера по имени и фамилии (логин пароль)
//    public User getUserByNameAndSurname(String name, String surname) {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<User> users = (List<User>) session.load(User.class, name);
//        User user = null;
//        for (User u : users) {
//            if (u.getSurname().equals(surname)) {
//                user = u;
//            }
//        }
//        return user;
//    }
}