package com.nix;

import com.nix.jdbcdaotask.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class Main {
    public static void main(String[] args) {

//        Role roleAdmin = new Role(1L, "Admin");
//        Role roleCleaner = new Role(2L, "Cleaner");
//        Role roleDirector = new Role(3L, "Director");
//
//        RoleDao roleDao = new JdbcRoleDao();
//        roleDao.create(roleAdmin);
//        roleDao.create(roleCleaner);
//        roleDao.create(roleDirector);
//
//        User userAdmin = new User(1L, "admin", "passwordA", "emailAdmin", 1L);
//        User userCleaner = new User(2L, "cleaner", "passwordC", "emailCleaner", 2L);
//        User userDirector = new User(3L, "ceo", "passwordCeo", "emailCeo", 3L);
//        User userWithAllFields = new User(11L, "FullUser", "passwordA",
//                "emailAdmin@email.com", "Jack", "Jonson",
//                new Date(new GregorianCalendar(2000, Calendar.SEPTEMBER, 5).getTimeInMillis()), 2L);

//        System.out.println("users created");


        UserDao userDao = new JdbcUserDao();
//        userDao.create(userAdmin);
//        userDao.create(userCleaner);
//        userDao.create(userDirector);
//        userDao.create(userWithAllFields);

//        System.out.println("users added");

        List<User> userList = userDao.findAll();
        for (User u : userList) {
            System.out.println(u.toString());
        }

//        System.out.println("users printed");
    }
}
