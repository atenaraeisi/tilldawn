package com.tilldawn.Model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Control.data.UserDataSQL;

import java.util.regex.Pattern;

public class User {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#%&*()$]).{8,}$");
    private static final String[] securityQuestions = new String[] {
        "What is the name of your first school?",
        "What was the name of your childhood best friend?",
        "In what city were you born?",
        "What is your favorite movie or book?",
        "What is your favorite food?",
    };
    private String username;
    private String password;
    private String selectedQuestion;
    private String answer;
    private Player player;
    private int score = 0;
    private int kills = 0;
    private double timeAlive = 0f;
    private transient Image avatar;



    public User(String username, String password, String selectedQuestion, String answer) {
        this.username = username;
        this.password = password;
        this.selectedQuestion = selectedQuestion;
        this.answer = answer;
        this.avatar = new Image(GameAssetManager.getGameAssetManager().getAvatarDrawables()[0]);
    }

    public int getKills() {
        return kills;
    }

    public void addKills(int kills) {
        this.kills += kills;
        UserDataSQL.getInstance().updateKills(username, this.kills);
    }

    public void addTimeAlive(double timeAlive) {
        if (timeAlive > this.timeAlive) this.timeAlive = timeAlive;
        UserDataSQL.getInstance().updateTimeAlive(username, this.timeAlive);
    }

    public double getTimeAlive() {
        return timeAlive;
    }

    public void setTimeAlive(double timeAlive) {
        this.timeAlive = timeAlive;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public static String[] getSecurityQuestions() {
        return securityQuestions;
    }

    public static Pattern getPasswordPattern() {
        return PASSWORD_PATTERN;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        UserDataSQL.getInstance().updateUsername(this.username, username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        UserDataSQL.getInstance().updatePassword(username, this.password);
    }

    public String getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(String selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void addScore(int score) {
        this.score += score;
        UserDataSQL.getInstance().updateScore(username, this.score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;

    }
}
