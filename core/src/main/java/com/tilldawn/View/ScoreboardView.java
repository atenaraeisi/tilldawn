package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.MainMenuController;
import com.tilldawn.Control.data.UserDataSQL;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.User;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.Menu.MainMenuView;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardView implements Screen {

    private Skin skin;
    private Stage stage;
    private Table table;
    private List<User> allUsers;
    private Comparator<User> currentComparator;

    public ScoreboardView(Skin skin) {
        this.skin = skin;
        this.allUsers = UserDataSQL.getInstance().getAllUsers();
        this.currentComparator = Comparator.comparingInt(User::getScore).reversed(); // پیش‌فرض مرتب‌سازی بر اساس score
    }

    private Table createScoreboardTable() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Label title = new Label("Scoreboard", skin.get("title", Label.LabelStyle.class));
        mainTable.add(title).padBottom(50).row();

        Table sortButtons = new Table();
        sortButtons.add(createSortButton("Score", Comparator.comparingInt(User::getScore).reversed())).padRight(10);
        sortButtons.add(createSortButton("Username", Comparator.comparing(User::getUsername))).padRight(10);
        sortButtons.add(createSortButton("Kills", Comparator.comparingInt(User::getKills).reversed())).padRight(10);
        sortButtons.add(createSortButton("Time Alive", Comparator.comparing(User::getTimeAlive).reversed()));

        Table userTable = new Table();
        userTable.defaults().pad(5);

        List<User> topUsers = allUsers.stream()
            .sorted(currentComparator)
            .limit(10)
            .collect(Collectors.toList());

        // Header row
        userTable.add(new Label("Rank", skin)).padRight(40);
        //userTable.add(new Label("Avatar", skin)).padRight(40);
        userTable.add(new Label("Username", skin)).padRight(40);
        userTable.add(new Label("Score", skin)).padRight(40);
        userTable.add(new Label("Kills", skin)).padRight(40);
        userTable.add(new Label("Time Alive", skin)).padRight(40).row();

        for (int i = 0; i < topUsers.size(); i++) {
            User user = topUsers.get(i);

            Label rankLabel = new Label((i + 1) + "", skin);
            Label usernameLabel = new Label(user.getUsername(), skin);
            Label scoreLabel = new Label(String.valueOf(user.getScore()), skin);
            Label killsLabel = new Label(String.valueOf(user.getKills()), skin);
            Label timeLabel = new Label(String.format("%.2f", user.getTimeAlive()), skin);

            if (i == 0) {
                rankLabel.setColor(Color.GOLD);
                usernameLabel.setColor(Color.GOLD);
                scoreLabel.setColor(Color.GOLD);
                killsLabel.setColor(Color.GOLD);
                timeLabel.setColor(Color.GOLD);
            }
            else if (i == 1) {
                rankLabel.setColor(Color.GRAY);
                usernameLabel.setColor(Color.GRAY);
                scoreLabel.setColor(Color.GRAY);
                killsLabel.setColor(Color.GRAY);
                timeLabel.setColor(Color.GRAY);
            }
            else if (i == 2) {
                rankLabel.setColor(Color.valueOf("CD7F32"));
                usernameLabel.setColor(Color.valueOf("CD7F32"));
                scoreLabel.setColor(Color.valueOf("CD7F32"));
                killsLabel.setColor(Color.valueOf("CD7F32"));
                timeLabel.setColor(Color.valueOf("CD7F32"));
            }

            if (Game.getCurrentUser() != null && user.getUsername().equals(Game.getCurrentUser().getUsername())) {
                rankLabel.setColor(Color.CYAN);
                usernameLabel.setColor(Color.CYAN);
                scoreLabel.setColor(Color.CYAN);
                killsLabel.setColor(Color.CYAN);
                timeLabel.setColor(Color.CYAN);
            }

            userTable.add(rankLabel).padRight(40);
            //userTable.add(user.getAvatar()).size(32, 32).padRight(40);
            userTable.add(usernameLabel).padRight(40);
            userTable.add(scoreLabel).padRight(40);
            userTable.add(killsLabel).padRight(40);
            userTable.add(timeLabel).padRight(40).row();
        }

        ScrollPane scrollPane = new ScrollPane(userTable, skin);
        mainTable.add(scrollPane).height(500).width(700).row();
        mainTable.add(new Label("Sort By:", skin)).left().row();
        mainTable.add(sortButtons).padBottom(20).row();


        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), skin));
            }
        });

        mainTable.add(backButton).padTop(20);
        return mainTable;
    }

    private TextButton createSortButton(String text, Comparator<User> comparator) {
        TextButton button = new TextButton(text, skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentComparator = comparator;
                table.clear();
                table.add(createScoreboardTable()).expand().fill();
            }
        });
        return button;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        allUsers = UserDataSQL.getInstance().getAllUsers();
        table = new Table();
        table.setFillParent(true);
        table.add(createScoreboardTable()).expand().fill();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.1f, 0.1f, 0.15f, 1);
        if (GameAssetManager.getGameAssetManager().isBlackAndWhiteEnabled()) {
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
            stage.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
        } else {
            Main.getBatch().setShader(null);
            stage.getBatch().setShader(null);
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
