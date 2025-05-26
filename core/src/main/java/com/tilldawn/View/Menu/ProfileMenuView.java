package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class ProfileMenuView implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin;
    private ProfileMenuController controller;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.05f, 0.05f, 0.1f, 1);
        if (GameAssetManager.getGameAssetManager().isBlackAndWhiteEnabled()) {
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
            stage.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
        } else {
            Main.getBatch().setShader(null);
            stage.getBatch().setShader(null);
        }
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleProfileMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
