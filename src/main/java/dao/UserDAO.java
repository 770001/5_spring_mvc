package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

/*
Интерфейс UserDAO, обязует выполнить следующие методы
 */
public interface UserDAO {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByNameAndSurname(String name, String surname); //новый метод для login
}