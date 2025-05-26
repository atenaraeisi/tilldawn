package com.tilldawn.Model;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
    private int score;
    private Image avatar;



    public User(String username, String password, String selectedQuestion, String answer) {
        this.username = username;
        this.password = password;
        this.selectedQuestion = selectedQuestion;
        this.answer = answer;
        this.avatar = new Image(GameAssetManager.getGameAssetManager().getAvatarDrawables()[0]);
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
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
