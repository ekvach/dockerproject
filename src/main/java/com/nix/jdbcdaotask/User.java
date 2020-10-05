package com.nix.jdbcdaotask;

import java.sql.Date;

public class User {

    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Long roleId;

    public User() {
    }

    // Constructor with required fields
    public User(Long id, String login, String password,
                String email, Long roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    // Constructor with required fields
    public User(Long id, String login, String password, String email,
                String firstName, String lastName, Date birthday, Long roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", roleId=" + roleId +
                '}';
    }
}
