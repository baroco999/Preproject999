package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserServiceImpl srv = new UserServiceImpl();
        srv.createUsersTable();

        User u1 = new User("Михаил", "Иванов", (byte) 27);
        srv.saveUser(u1.getName(), u1.getLastName(), u1.getAge());

        User u2 = new User("Дмитрий", "Морозов", (byte) 30);
        srv.saveUser(u2.getName(), u2.getLastName(), u2.getAge());

        User u3 = new User("Елена", "Кузнецова", (byte) 19);
        srv.saveUser(u3.getName(), u3.getLastName(), u3.getAge());

        User u4 = new User("Наталья", "Петрова", (byte) 24);
        srv.saveUser(u4.getName(), u4.getLastName(), u4.getAge());

        System.out.println(srv.getAllUsers().toString());
        srv.cleanUsersTable();
        srv.dropUsersTable();
    }
}
