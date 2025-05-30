package com.tilldawn.Control;

import com.tilldawn.Model.User;

import java.sql.*;

public class UserDataSQL {
    private static UserDataSQL instance;
    private final static String DBURL = "jdbc:sqlite:data/users.db";

    private UserDataSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "username VARCHAR(255) NOT NULL UNIQUE, "
            + "password VARCHAR(255) NOT NULL, "
            + "selectedQuestion TEXT, "
            + "answer TEXT, "
            + "score INTEGER DEFAULT 0"
            + ");";


        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
        catch (SQLException e) {
            System.err.println("Can't create a table for SQLite: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static UserDataSQL getInstance() {
        if (instance == null)
            instance = new UserDataSQL();
        return instance;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, selectedQuestion, answer, score) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSelectedQuestion());
            pstmt.setString(4, user.getAnswer());
            pstmt.setInt(5, user.getScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can't save user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DBURL);
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

        try (Connection conn = DriverManager.getConnection(DBURL);
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
        try (Connection conn = DriverManager.getConnection(DBURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, oldUsername);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Can't update username: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DBURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Can't update password: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateScore(String username, int score) {
        String sql = "UPDATE users SET score = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DBURL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can't update score: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
