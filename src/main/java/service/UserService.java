package service;

import model.User;

import java.util.List;

/*
Интерфейс сервиса.
 */
public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByNameAndSurname(String reqName, String reqSurname); //новый метод для login
}
