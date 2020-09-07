package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserService srv = new UserServiceImpl();
        srv.createUsersTable();

        User[] users = new User[] { new User("Михаил", "Иванов", (byte) 27),
                new User("Дмитрий", "Морозов", (byte) 30),
                new User("Елена", "Кузнецова", (byte) 19),
                new User("Наталья", "Петрова", (byte) 24)
        };

        for (User u : users) {
            srv.saveUser(u.getName(), u.getLastName(), u.getAge());
        }

        System.out.println(srv.getAllUsers().toString());
        srv.cleanUsersTable();
        srv.dropUsersTable();
    }
}
