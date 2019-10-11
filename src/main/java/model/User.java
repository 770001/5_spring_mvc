package model;

import javax.persistence.*;

/*
Модель
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public User(long id, String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.age = age;
    }

    public User(String name, String surname, String role, int age) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.age = age;
    }

    public User(long id, String name, String surname, String role, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.age = age;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
