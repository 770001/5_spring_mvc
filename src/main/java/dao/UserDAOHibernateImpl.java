package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
Реализация на Hibernate, синглтон. Настройки из DBHelper.
 */
@Repository
public class UserDAOHibernateImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //взять всех юзеров
    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User").list();
        return allUsers;
    }

    //добавить юзера
    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    //обновить юзера
    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    //взять юзера по id
    @Override
    public User getUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    //удалить юзера
    @Override
    public void deleteUser(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }

    //достаем юзера по имени и фамилии (логин пароль)
    @Override
    public User getUserByNameAndSurname(String name, String surname) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = (List<User>) session.load(User.class, name);
        User user = null;
        for (User u : users) {
            if (u.getSurname().equals(surname)) {
                user = u;
            }
        }
        return user;
    }
}