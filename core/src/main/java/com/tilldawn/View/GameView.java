package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    OrthographicCamera camera;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600); // ابعاد دوربین
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
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

        Main.getBatch().end();
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
