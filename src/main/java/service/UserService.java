package service;

import model.User;

import java.util.List;

/*
Интерфейс сервиса.
 */
public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(long id);
    boolean updateUser(User user);
    boolean deleteUser(long id);

    User getUserByNameAndSurname(String reqName, String reqSurname); //новый метод для login
}
