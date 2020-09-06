package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection conn = Util.toMySql();
            Statement st = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age SMALLINT CHECK(Age >0 AND Age < 100))";
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection conn = Util.toMySql();
            Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = Util.toMySql()) {
            String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
            System.out.println("User с именем – " + name + " " + lastName + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection conn = Util.toMySql()) {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ResultSet rs;
        LinkedList<User> list = new LinkedList<>();

        try(Connection conn = Util.toMySql();
            Statement st = conn.createStatement()) {
            rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                list.addLast(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
                list.getLast().setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try(Connection conn = Util.toMySql();
            Statement st = conn.createStatement()) {
            st.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
