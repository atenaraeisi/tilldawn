package com.tilldawn.Control.data;

import com.tilldawn.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataSQL {
    private static UserDataSQL instance;
    private final static String DB_URL = "jdbc:sqlite:data/users.db";

    private UserDataSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username VARCHAR(255) NOT NULL UNIQUE, " +
            "password VARCHAR(255) NOT NULL, " +
            "selectedQuestion TEXT, " +
            "answer TEXT, " +
            "score INTEGER DEFAULT 0, " +
            "kills INTEGER DEFAULT 0, " +
            "timeAlive REAL DEFAULT 0.0" +
            ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Can't create a table for SQLite: " + e.getMessage());
            e.printStackTrace();
        }

        // Add missing columns manually (optional safety)
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE users ADD COLUMN kills INTEGER DEFAULT 0");
        } catch (SQLException ignored) {}
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("ALTER TABLE users ADD COLUMN timeAlive REAL DEFAULT 0.0");
        } catch (SQLException ignored) {}
    }


    public static UserDataSQL getInstance() {
        if (instance == null)
            instance = new UserDataSQL();
        return instance;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, selectedQuestion, answer, score, kills, timeAlive) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSelectedQuestion());
            pstmt.setString(4, user.getAnswer());
            pstmt.setInt(5, user.getScore());
            pstmt.setInt(6, user.getKills());
            pstmt.setDouble(7, user.getTimeAlive());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can't save user: " + e.getMessage());
            e.printStackTrace();
        }

        List<User> allUsers = getAllUsers();
        UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String selectedQuestion = rs.getString("selectedQuestion");
                String answer = rs.getString("answer");
                int score = rs.getInt("score");
                int kills = rs.getInt("kills");
                double timeAlive = rs.getDouble("timeAlive");
                User user = new User(username, password, selectedQuestion, answer);
                user.setScore(score);
                user.setKills(kills);
                user.setTimeAlive(timeAlive);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                    username,
                    rs.getString("password"),
                    rs.getString("selectedQuestion"),
                    rs.getString("answer")
                );
                user.setScore(rs.getInt("score"));
                user.setKills(rs.getInt("kills"));
                user.setTimeAlive(rs.getDouble("timeAlive"));

                return user;
            }
        } catch (SQLException e) {
            System.err.println("There was a problem while trying to get user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Can't delete user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateUsername(String oldUsername, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, oldUsername);
            pstmt.executeUpdate();
            List<User> allUsers = getAllUsers();
            UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
        }
        catch (SQLException e) {
            System.err.println("Can't update username: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            List<User> allUsers = getAllUsers();
            UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
        }
        catch (SQLException e) {
            System.err.println("Can't update password: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateScore(String username, int score) {
        String sql = "UPDATE users SET score = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            List<User> allUsers = getAllUsers();
            UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
        } catch (SQLException e) {
            System.err.println("Can't update score: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateKills(String username, int kills) {
        String sql = "UPDATE users SET kills = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, kills);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            List<User> allUsers = getAllUsers();
            UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
        } catch (SQLException e) {
            System.err.println("Can't update kills: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateTimeAlive(String username, double timeAlive) {
        String sql = "UPDATE users SET timeAlive = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, timeAlive);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            List<User> allUsers = getAllUsers();
            UserJsonUtil.saveUsersToJson(allUsers, "data/users.json");
        } catch (SQLException e) {
            System.err.println("Can't update timeAlive: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
