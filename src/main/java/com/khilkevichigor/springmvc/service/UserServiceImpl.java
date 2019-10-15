package com.khilkevichigor.springmvc.service;

import com.khilkevichigor.springmvc.dao.UserDAO;
import com.khilkevichigor.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/*
Реализация сервиса.
 */
@Service("userService") //класс который является сервисом
//@Scope("singleton")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional //класс поддерживает транзакции
    //взять всех юзеров
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    @Transactional
    //взять юзера по id
    public User getUserById(long id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    //добавить юзера
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    //обновить юзера
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    //удалить юзера
    public void deleteUser(long id) {
        this.userDAO.deleteUser(id);
    }
}