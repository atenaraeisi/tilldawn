package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.EnemyController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;

import static com.tilldawn.Control.GameController.WIN_TIME;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    OrthographicCamera camera;
    private ProgressBar timeProgressBar;
    private float timeElapsed = 0f; // زمان سپری شده (ثانیه)
    ProgressBar healthBar;
    private Label killCountLabel;
    private Label killCount;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600); // ابعاد دوربین
        controller.setView(this);

        ProgressBar.ProgressBarStyle style = skin.get("mana", ProgressBar.ProgressBarStyle.class);
        timeProgressBar = new ProgressBar(0, WIN_TIME, 1, false, style); // 20 دقیقه = 1200 ثانیه
        timeProgressBar.setValue(0);
        timeProgressBar.setAnimateDuration(0.25f);
        timeProgressBar.setSize(300, 5);

        healthBar = new ProgressBar(0, 100, 10, false, skin.get("health", ProgressBar.ProgressBarStyle.class));
        healthBar.setValue(100);
        healthBar.setSize(300, 5);
        healthBar.setAnimateDuration(0.2f);

        killCountLabel = new Label("Kill:", skin);
        killCountLabel.setColor(Color.WHITE);
        killCount = new Label("", skin);
        killCount.setColor(Color.WHITE);
        killCount.setText(Game.getCurrentPlayer().getKillCount());

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
        timeProgressBar.setPosition(0 , stage.getHeight() - 50);
        healthBar.setPosition(320, stage.getHeight() - 50);
        killCountLabel.setPosition(640, stage.getHeight() - 50);
        killCount.setPosition(690, stage.getHeight() - 35);

        stage.addActor(timeProgressBar);
        stage.addActor(healthBar);
        stage.addActor(killCountLabel);
        stage.addActor(killCount);

    }

    @Override
    public void render(float delta) {
        camera.position.set(Game.getCurrentPlayer().getPosX(), Game.getCurrentPlayer().getPosY(), 0); // دوربین دنبال بازیکن
        // ابعاد دوربین
        float viewportWidth = camera.viewportWidth;
        float viewportHeight = camera.viewportHeight;

        // ابعاد تصویر پس‌زمینه
        float backgroundWidth = controller.getWorldController().getBackgroundTexture().getWidth();
        float backgroundHeight = controller.getWorldController().getBackgroundTexture().getHeight();

        // محاسبه موقعیت دوربین محدودشده
        float cameraX = MathUtils.clamp(Game.getCurrentPlayer().getPosX(), viewportWidth / 2f, backgroundWidth - viewportWidth / 2f);
        float cameraY = MathUtils.clamp(Game.getCurrentPlayer().getPosY(), viewportHeight / 2f, backgroundHeight - viewportHeight / 2f);

        // تنظیم موقعیت دوربین
        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined); // اتصال دوربین به Main.getBatch()

        Main.getBatch().begin();
        Main.getBatch().draw(controller.getWorldController().getBackgroundTexture(), 0, 0); // بک‌گراند در (0,0) یا موقعیت واقعی خودش
        controller.updateGame();

        EnemyController.render(Main.getBatch());

        Main.getBatch().end();

        // آپدیت زمان
        timeElapsed += delta;
        timeProgressBar.setValue(timeElapsed);
        healthBar.setValue(Game.getCurrentPlayer().getPlayerHealth());

        killCount.setText(Game.getCurrentPlayer().getKillCount());

        //TODO
        if (Game.getCurrentPlayer().getPlayerHealth() <= 0) {
            System.out.println("You lose");
        }

        //TODO
        if (timeElapsed >= WIN_TIME) {
            System.out.println("YOU WON");
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
