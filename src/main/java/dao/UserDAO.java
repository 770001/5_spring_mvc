package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

/*
Интерфейс UserDAO, обязует выполнить следующие методы
 */
public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    void addUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    User getUserById(long id) throws SQLException;
    boolean deleteUser(long id) throws SQLException;

    User getUserByNameAndSurname(String name, String surname); //новый метод для login
}