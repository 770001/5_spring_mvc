package service;

import dao.UserDAO;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/*
Реализация сервиса.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //взять всех юзеров
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    //взять юзера по id
    @Override
    @Transactional
    public User getUserById(long id) {
        return this.userDAO.getUserById(id);
    }

    //добавить юзера
    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    //обновить юзера
    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    //удалить юзера
    @Override
    @Transactional
    public void deleteUser(long id) {
        this.userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    //получаем юзера по имени и фамилии (логин пароль)
    public User getUserByNameAndSurname(String name, String surname) {
        return this.userDAO.getUserByNameAndSurname(name, surname);
    }
}