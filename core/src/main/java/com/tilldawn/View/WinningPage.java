package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.Menu.MainMenuController;
import com.tilldawn.Control.data.UserDataSQL;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.View.Menu.MainMenuView;

public class WinningPage implements Screen {

    private Stage stage;
    private Label username;
    private Label score;
    private Label killsCount;
    private Label timeAlive;

    public WinningPage(Skin skin) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Player player = Game.getCurrentPlayer();
        Label title = new Label("You Win!", skin, "title");
        Label alive = new Label("You Survived!", skin);
        if (player.getUser() != null) {
            username = new Label("username: " + player.getUser().getUsername(), skin);
        } else username = new Label("name: guest", skin);
        int scoreNumber = player.getKillCount() * 1200;
        if (player.getUser() != null) {
            player.getUser().addScore(scoreNumber);
            player.getUser().addKills(player.getKillCount());
            player.getUser().addTimeAlive(GameController.getTotalGameTime());
        }
        score = new Label("Score: " + scoreNumber, skin);
        int killsCountNumber = player.getKillCount();
        killsCount = new Label("Kill count: " +  killsCountNumber, skin);
        int time = 20;
        timeAlive = new Label("time being alive: " + time + " minute", skin);
        TextButton backButton = new TextButton("Back to Menu", skin);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        });

        GameAssetManager.getGameAssetManager().getWonSound().play();

        table.add(title).padBottom(40).row();
        table.add(alive).padBottom(40).row();
        table.add(username).padBottom(40).row();
        table.add(score).padBottom(40).row();
        table.add(killsCount).padBottom(40).row();
        table.add(timeAlive).padBottom(40).row();
        table.add(backButton).width(400).height(100);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
