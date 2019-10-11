package service;

import dao.UserDAO;
import dao.UserDAOHibernateImpl;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

/*
Реализация сервиса, синглтон.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private static UserServiceImpl userServiceImpl;

    private UserServiceImpl(SessionFactory sessionFactory) {
        this.userDAO = new UserDAOHibernateImpl(sessionFactory);
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl(DBHelper.getInstance().getSessionFactory());
        }
        return userServiceImpl;
    }

    //взять всех юзеров
    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> getAllUsers = null;
        try {
            getAllUsers = userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAllUsers;
    }

    //взять юзера по id
    @Override
    @Transactional
    public User getUserById(long id) {
        User user = null;
        try {
            user = userDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //добавить юзера
    @Override
    @Transactional
    public void addUser(User user) {
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //обновить юзера
    @Override
    @Transactional
    public boolean updateUser(User user) {
        boolean updateUser = false;
        try {
            updateUser = userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateUser;
    }

    //удалить юзера
    @Override
    @Transactional
    public boolean deleteUser(long id) {
        boolean deleteUser = false;
        try {
            deleteUser = userDAO.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteUser;
    }

    @Override
    @Transactional
    //получаем юзера по имени и фамилии (логин пароль)
    public User getUserByNameAndSurname(String name, String surname) {
        User userLogin = userDAO.getUserByNameAndSurname(name, surname);
        return userLogin;
    }
}