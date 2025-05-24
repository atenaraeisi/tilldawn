package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.EnemyController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.WeaponController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameState;
import com.tilldawn.Model.Player;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tilldawn.Control.GameController.WIN_TIME;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    public static OrthographicCamera camera;
    private ProgressBar timeProgressBar;
    private float timeElapsed = 0f;
    ProgressBar healthBar;
    private Label killCountLabel;
    private Label killCount;
    private Label levelLabel;
    private ProgressBar xpProgressBar;
    private boolean isAbilitySelectionActive = false;
    private List<Ability> randomAbilities;
    private Dialog abilityDialog;
    private Skin skin;




    public GameView(GameController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600); // ابعاد دوربین
        controller.setView(this);

        ProgressBar.ProgressBarStyle style = skin.get("mana", ProgressBar.ProgressBarStyle.class);
        timeProgressBar = new ProgressBar(0, WIN_TIME, 1, false, style);
        timeProgressBar.setValue(0);
        timeProgressBar.setAnimateDuration(0.25f);
        timeProgressBar.setSize(300, 5);

        Player player = Game.getCurrentPlayer();
        healthBar = new ProgressBar(0, player.getMaxHp(), 10, false, skin.get("health", ProgressBar.ProgressBarStyle.class));
        healthBar.setValue(100);
        healthBar.setSize(300, 5);
        healthBar.setAnimateDuration(0.2f);

        killCountLabel = new Label("Kill:", skin);
        killCountLabel.setColor(Color.WHITE);
        killCount = new Label("", skin);
        killCount.setColor(Color.WHITE);
        killCount.setText(Game.getCurrentPlayer().getKillCount());

        xpProgressBar = new ProgressBar(0, 100, 1, false, skin.get("default-horizontal", ProgressBar.ProgressBarStyle.class));
        xpProgressBar.setValue(0);
        xpProgressBar.setAnimateDuration(0.25f);
        xpProgressBar.setSize(300, 10);

        levelLabel = new Label("Level: 1", skin);
        levelLabel.setColor(Color.WHITE);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);       // برای دیالوگ و دکمه‌ها
        multiplexer.addProcessor(this);        // برای کنترل شلیک، موس، کیبورد
        Gdx.input.setInputProcessor(multiplexer);

        timeProgressBar.setPosition(0 , stage.getHeight() - 50);
        healthBar.setPosition(320, stage.getHeight() - 50);
        killCountLabel.setPosition(640, stage.getHeight() - 50);
        killCount.setPosition(690, stage.getHeight() - 35);

        // موقعیت نوار XP و لول
        xpProgressBar.setPosition(stage.getWidth() - 320, stage.getHeight() - 60);
        levelLabel.setPosition(stage.getWidth() - 320, stage.getHeight() - 40);

        stage.addActor(timeProgressBar);
        stage.addActor(healthBar);
        stage.addActor(killCountLabel);
        stage.addActor(killCount);
        stage.addActor(xpProgressBar);
        stage.addActor(levelLabel);

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
        if (!isAbilitySelectionActive) {
            controller.updateGame();
        }


        EnemyController.render(Main.getBatch());

        Main.getBatch().end();

        // آپدیت زمان
        timeElapsed += delta;
        timeProgressBar.setValue(timeElapsed);
        healthBar.setValue(Game.getCurrentPlayer().getPlayerHealth());

        killCount.setText(Game.getCurrentPlayer().getKillCount());

        // به‌روزرسانی نوار XP و لول
        int currentXP = Game.getCurrentPlayer().getXp();
        int level = Game.getCurrentPlayer().getLevel();
        int xpForNextLevel = Game.getCurrentPlayer().getXPForNextLevel();

        if (currentXP >= xpForNextLevel && !isAbilitySelectionActive) {
            Game.getCurrentPlayer().addLevel(1);
            Game.getCurrentPlayer().setXp(0);
            Game.setGameState(GameState.PAUSED);
            showAbilitySelectionDialog();
        }


        xpProgressBar.setRange(0, xpForNextLevel); // مقدار مورد نیاز برای لول بعدی
        xpProgressBar.setValue(currentXP);
        levelLabel.setText("Level: " + level);

        //TODO
        if (Game.getCurrentPlayer().getPlayerHealth() <= 0) {
            //System.out.println("You lose");
        }

        //TODO
        if (timeElapsed >= WIN_TIME) {
            //System.out.println("YOU WON");
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    private void showAbilitySelectionDialog() {
        isAbilitySelectionActive = true;

        // انتخاب ۳ ابیلیتی تصادفی
        List<Ability> abilities = new ArrayList<>(List.of(Ability.values()));
        Collections.shuffle(abilities);
        randomAbilities = abilities.subList(0, 3);

        abilityDialog = new Dialog("Choose Your Ability\n", skin) {
            protected void result(Object object) {
                if (object instanceof Ability) {
                    Ability chosen = (Ability) object;
                    applyAbility(chosen);
                    isAbilitySelectionActive = false;
                    System.out.println("Ability selected: " + chosen.getDescription());
                    this.hide();
                }

            }
        };
        VerticalGroup abilityGroup = new VerticalGroup();
        abilityGroup.columnAlign(Align.center);

        for (Ability ability : randomAbilities) {
            Texture texture = ability.getTexture();
            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = new TextureRegionDrawable(new TextureRegion(texture));
            ImageButton button = new ImageButton(style);
            Label label = new Label(ability.name(), skin);

            VerticalGroup singleAbilityGroup = new VerticalGroup();
            singleAbilityGroup.addActor(button);
            singleAbilityGroup.addActor(label);

            Button button2 = new Button(skin);
            button2.add(singleAbilityGroup);

            abilityDialog.button(button2, ability);
            button2.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Game.setGameState(GameState.PLAYING);
                    System.out.println("Button Pressed");
                }
            });


        }


        // جلوگیری از بسته‌شدن دیالوگ با کلیک بیرون
        abilityDialog.setModal(true);
        abilityDialog.setMovable(false);
        abilityDialog.setResizable(false);

        abilityDialog.show(stage); // نمایش دیالوگ
    }

    private void applyAbility(Ability ability) {
        Player player = Game.getCurrentPlayer();
        switch (ability) {
            case VITALITY:
                player.increaseMaxHp(20);
                break;
            case DAMAGER :
                player.getEquippedWeapon().increaseDamage();
                controller.getWeaponController().startIncrease();
                break;
            case PROCREASE :
                player.getEquippedWeapon().increaseProjectilePerShot(1);
                break;
            case AMOCREASE :
                player.getEquippedWeapon().increaseMaxAmmo(5);
                break;
            case SPEEDY :
                player.activateSpeedBoost(10);
                controller.getPlayerController().startBoost();
                break;
        }
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
        if (keycode == Game.getReloadButton()) {
            controller.getWeaponController().startReload();
        }
        return true;
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
