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
import com.tilldawn.Model.*;
import com.tilldawn.View.Menu.MainMenuView;

public class GameOverPage implements Screen {
    private Stage stage;
    private Label username;
    private Label score;
    private Label killsCount;
    private Label timeAlive;

    public GameOverPage(Skin skin) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("Game Over", skin, "title");
        Label death = new Label("You died", skin);
        TextButton retryButton = new TextButton("Retry", skin);
        TextButton backButton = new TextButton("Back to Menu", skin);

        Player player = Game.getCurrentPlayer();
        if (player.getUser() != null) {
            username = new Label("username: " + player.getUser().getUsername(), skin);
        } else username = new Label("name: guest", skin);
        int scoreNumber = (int) (player.getKillCount() * GameController.getTotalGameTime());
        if (player.getUser() != null) {
            player.getUser().addScore(scoreNumber);
            player.getUser().addKills(player.getKillCount());
            player.getUser().addTimeAlive(GameController.getTotalGameTime());
        }
        score = new Label("Score: " + scoreNumber, skin);
        int killsCountNumber = player.getKillCount();
        killsCount = new Label("Kill count: " +  killsCountNumber, skin);
        int time = (int) GameController.getTotalGameTime();
        timeAlive = new Label("time being alive: " + time + " second", skin);

        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().getScreen().dispose();
                Game.setGameState(GameState.PLAYING);
                WeaponType weaponType = Game.getCurrentPlayer().getEquippedWeapon().getType();
                Game.setCurrentPlayer(new Player(Game.getCurrentUser(), Game.getCurrentPlayer().getCharacter()));
                Game.getCurrentPlayer().setEquippedWeapon(new Weapon(weaponType));
                GameController.setTotalGameTime(0f);
                Main.getMain().setScreen(new GameView(new GameController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        });

        GameAssetManager.getGameAssetManager().getLoseSound().play();

        table.add(title).padBottom(40).row();
        table.add(death).padBottom(40).row();
        table.add(username).padBottom(40).row();
        table.add(score).padBottom(40).row();
        table.add(killsCount).padBottom(40).row();
        table.add(timeAlive).padBottom(40).row();
        table.add(retryButton).width(400).height(100).padBottom(20).row();
        table.add(backButton).width(400).height(100);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0, 0, 1);
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
