package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.EntryMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

public class EntryMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private EntryMenuController controller;
    private Texture logo;
    private Texture leftSide, rightSide;
    private final TextButton signUpButton;
    private final TextButton loginButton;
    private final TextButton guestButton;
    private final TextButton exitButton;

    public EntryMenuView(EntryMenuController controller , Skin skin) {
        this.skin = skin;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle  textOnlyStyle = new TextButton.TextButtonStyle();
        textOnlyStyle.font = bigFont;
        textOnlyStyle.fontColor = Color.RED;
        textOnlyStyle.overFontColor = Color.WHITE;
        textOnlyStyle.downFontColor = Color.GRAY;
        this.controller = controller;
        signUpButton = new TextButton("Sign Up",  textOnlyStyle);
        loginButton = new TextButton("Login",  textOnlyStyle);
        guestButton = new TextButton("Enter as a guest",  textOnlyStyle);
        logo = new Texture("images/T_20Logo.png");
        leftSide = new Texture("images/T_TitleLeaves.png");
        rightSide = new Texture("images/T_TitleLeavesR.png");
        exitButton = new TextButton("Exit",  textOnlyStyle);

        controller.setView(this);
    }

    @Override
    public void show() {
        GameAssetManager.getGameAssetManager().loadShaders();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(Gdx.files.internal("Sprite/T/T_Cursor.png"));
        Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose();

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(signUpButton).width(500).padTop(250f).padBottom(60f).row();
        table.add(loginButton).width(500).padBottom(60f).row();
        table.add(guestButton).width(500).padBottom(60f).row();
        table.add(exitButton).width(500).row();

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        MainMenuView.initialRender(stage);
        stage.getBatch().begin();
        stage.getBatch().draw(logo, 551, 580, 818, 450);
        stage.getBatch().draw(leftSide, 0, 0, 590, Gdx.graphics.getHeight());
        stage.getBatch().draw(rightSide, Gdx.graphics.getWidth() - 590, 0, 590, Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
        controller.handleEntryMenuButtons();
    }

    @Override
    public void resize(int width, int height) { stage.getViewport().update(width, height, true); }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
        logo.dispose();
        leftSide.dispose();
        rightSide.dispose();
    }

    public TextButton getGuestButton() {
        return guestButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }
}
