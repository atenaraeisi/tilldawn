package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.Menu.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.Game;
import com.tilldawn.View.Menu.MainMenuView;

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
    private TextButton pauseButton;
    private Table pauseMenu;
    private Image pauseOverlay;
    private List<Ability> randomAbilities;
    private Dialog abilityDialog;
    private Skin skin;
    private Image vitalityImage;
    private Image damagerImage;
    private Image procreaseImage;
    private Image amocreaseImage;
    private Image speedyImage;
    Texture bulletTexture;
    private final ArrayList<Image> bulletIcons = new ArrayList<>();
    private Table bulletTable;
    private int lastAmmo;
    private Sprite darknessOverlay;




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
        healthBar = new ProgressBar(0, player.getMaxHp(), 2, false, skin.get("health", ProgressBar.ProgressBarStyle.class));
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

        pauseButton = new TextButton("Pause", skin);
        pauseButton.setSize(200, 100);

        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameState.PAUSED);
                pauseOverlay.setVisible(true);
                pauseMenu.setVisible(true);
            }
        });

        Texture vitality = GameAssetManager.getGameAssetManager().getVitalityTexture();
        vitalityImage = new Image(new TextureRegionDrawable(new TextureRegion(vitality)));
        vitalityImage.setSize(vitality.getWidth(), vitality.getHeight());
        vitalityImage.setColor(0, 0, 0, 1);


        Texture damager = GameAssetManager.getGameAssetManager().getDamagerTexture();
        damagerImage = new Image(new TextureRegionDrawable(new TextureRegion(damager)));
        damagerImage.setSize(damager.getWidth(), damager.getHeight());
        damagerImage.setColor(0, 0, 0, 1);

        Texture procrease = GameAssetManager.getGameAssetManager().getProCreaseTexture();
        procreaseImage = new Image(new TextureRegionDrawable(new TextureRegion(procrease)));
        procreaseImage.setSize(procrease.getWidth(), procrease.getHeight());
        procreaseImage.setColor(0, 0, 0, 1);

        Texture amocrease = GameAssetManager.getGameAssetManager().getAmoCreaseTexture();
        amocreaseImage = new Image(new TextureRegionDrawable(new TextureRegion(amocrease)));
        amocreaseImage.setSize(amocrease.getWidth(), amocrease.getHeight());
        amocreaseImage.setColor(0, 0, 0, 1);

        Texture speedy = GameAssetManager.getGameAssetManager().getSpeedyTexture();
        speedyImage = new Image(new TextureRegionDrawable(new TextureRegion(speedy)));
        speedyImage.setSize(speedy.getWidth(), speedy.getHeight());
        speedyImage.setColor(0, 0, 0, 1);


        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture whiteTex = new Texture(pixmap);
        pixmap.dispose();

        pauseOverlay = new Image(new TextureRegionDrawable(new TextureRegion(whiteTex)));
        pauseOverlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pauseOverlay.setColor(0, 0, 0, 0.7f);
        pauseOverlay.setVisible(false);

        pauseMenu = new Table();
        pauseMenu.setFillParent(true);
        pauseMenu.setVisible(false);

        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameState.PLAYING);
                pauseOverlay.setVisible(false);
                pauseMenu.setVisible(false);
            }
        });

        // Header
        pauseMenu.add(new Label("Game Paused", skin, "title")).padBottom(30).colspan(2).center().row();

// Icon row
        Table iconRow = new Table();
        iconRow.add(vitalityImage).size(40).pad(5);
        iconRow.add(damagerImage).size(40).pad(5);
        iconRow.add(procreaseImage).size(40).pad(5);
        iconRow.add(amocreaseImage).size(40).pad(5);
        iconRow.add(speedyImage).size(40).pad(5);
        pauseMenu.add(iconRow).colspan(2).padBottom(20).row();

// Buttons
        pauseMenu.add(resumeButton).pad(10).fillX().colspan(2).row();

        TextButton cheatButton = getTextCheatButton(skin);
        pauseMenu.add(cheatButton).pad(10).fillX().colspan(2).row();

        TextButton grayscaleButton = new TextButton("Toggle Grayscale", skin);
        grayscaleButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                GameAssetManager.getGameAssetManager().toggleBlackAndWhite();            }
        });
        pauseMenu.add(grayscaleButton).pad(10).fillX().colspan(2).row();

        TextButton saveExitButton = new TextButton("Save & Exit", skin);
        saveExitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //TODO
                // controller.saveGame();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        });
        pauseMenu.add(saveExitButton).pad(10).fillX().colspan(2).row();

        TextButton giveUpButton = new TextButton("Give Up", skin);
        giveUpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Game.setGameState(GameState.GAME_OVER);
            }
        });
        pauseMenu.add(giveUpButton).pad(10).fillX().colspan(2).row();

        lastAmmo = Game.getCurrentPlayer().getEquippedWeapon().getAmmo();

    }

    private TextButton getTextCheatButton(Skin skin) {
        TextButton cheatButton = new TextButton("Cheat Codes", skin);
        cheatButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                Dialog dialog = new Dialog("\nError", skin);
                dialog.text("\n   - Skip Time by BACKSLASH Key    \n\n" +
                    "   - Level Up by L Key  \n\n" +
                    "   - Gain HP by H key   \n\n" +
                    "   - Increase one projectile by O key  \n\n" +
                    "   - Boss Fight by B key    ");
                dialog.button("OK");
                dialog.show(stage);
            }
        });
        return cheatButton;
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

        pauseButton.setPosition(stage.getWidth() - 220, 50);


        bulletIcons.clear(); // برای اطمینان از پاک شدن لیست قبلی
        bulletTable = new Table();
        bulletTable.setName("bulletTable"); // برای یافتن راحت در Stage

        bulletTexture = GameAssetManager.getGameAssetManager().getSmgReload_idle0_tex();
        for (int i = 0; i < Game.getCurrentPlayer().getEquippedWeapon().getAmmo(); i++) {
            Image bulletIcon = new Image(new TextureRegionDrawable(new TextureRegion(bulletTexture)));
            bulletIcon.setSize(16, 16);
            bulletTable.add(bulletIcon).pad(2);
            bulletIcons.add(bulletIcon);
        }
        bulletTable.pack();
        bulletTable.setPosition(stage.getWidth() - 320, stage.getHeight() - 100);
        stage.addActor(bulletTable);

        Texture maskTexture = new Texture(Gdx.files.internal("mask.png"));
        darknessOverlay = new Sprite(maskTexture);
        darknessOverlay.setColor(0, 0, 0, 0.5f);
        darknessOverlay.setSize(1500, 800);



        stage.addActor(timeProgressBar);
        stage.addActor(healthBar);
        stage.addActor(killCountLabel);
        stage.addActor(killCount);
        stage.addActor(xpProgressBar);
        stage.addActor(levelLabel);
        stage.addActor(pauseButton);
        stage.addActor(pauseOverlay);
        stage.addActor(pauseMenu);

    }



    @Override
    public void render(float delta) {
        camera.position.set(Game.getCurrentPlayer().getPosX(), Game.getCurrentPlayer().getPosY(), 0);
        float viewportWidth = camera.viewportWidth;
        float viewportHeight = camera.viewportHeight;

        float backgroundWidth = controller.getWorldController().getBackgroundTexture().getWidth();
        float backgroundHeight = controller.getWorldController().getBackgroundTexture().getHeight();

        float cameraX = MathUtils.clamp(Game.getCurrentPlayer().getPosX(), viewportWidth / 2f, backgroundWidth - viewportWidth / 2f);
        float cameraY = MathUtils.clamp(Game.getCurrentPlayer().getPosY(), viewportHeight / 2f, backgroundHeight - viewportHeight / 2f);

        camera.position.set(cameraX, cameraY, 0);
        camera.update();


        Main.getBatch().setProjectionMatrix(camera.combined);

        if (GameAssetManager.getGameAssetManager().isBlackAndWhiteEnabled()) {
            Main.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
            stage.getBatch().setShader(GameAssetManager.getGameAssetManager().getGrayscaleShader());
        } else {
            Main.getBatch().setShader(null);
            stage.getBatch().setShader(null);
        }

        Main.getBatch().begin();
        Main.getBatch().draw(controller.getWorldController().getBackgroundTexture(), 0, 0);





        if (!isAbilitySelectionActive) {
            controller.updateGame();
        }
        Player player = Game.getCurrentPlayer();
        darknessOverlay.setPosition(
            player.getPlayerSprite().getX() + player.getRect().getWidth() / 2f - darknessOverlay.getWidth() / 2f,
            player.getPlayerSprite().getY() + player.getRect().getHeight() / 2f - darknessOverlay.getHeight() / 2f
        );
        darknessOverlay.draw(Main.getBatch());

        controller.getEnemyController().render(Main.getBatch());

        Main.getBatch().end();

        timeElapsed += delta;
        timeProgressBar.setValue(timeElapsed);
        healthBar.setValue(Game.getCurrentPlayer().getPlayerHealth());
        if (Game.getCurrentPlayer().getPlayerHealth() <= 10 && Game.isSfx_enabled()) {
            GameAssetManager.getGameAssetManager().getHealthAlarmSound().play();
        }

        killCount.setText(Game.getCurrentPlayer().getKillCount());

        if (lastAmmo > Game.getCurrentPlayer().getEquippedWeapon().getAmmo()) {
            removeBulletIconOnShoot();
            refillBulletIcons(Game.getCurrentPlayer().getEquippedWeapon().getAmmo());
        }

        // به‌روزرسانی نوار XP و لول
        int currentXP = Game.getCurrentPlayer().getXp();
        int level = Game.getCurrentPlayer().getLevel();
        int xpForNextLevel = Game.getCurrentPlayer().getXPForNextLevel();

        if (currentXP >= xpForNextLevel && !isAbilitySelectionActive) {
            if (Game.isSfx_enabled()) GameAssetManager.getGameAssetManager().getLevelUpSound().play();
            Game.getCurrentPlayer().addLevel(1);
            Game.getCurrentPlayer().setXp(0);
            Game.setGameState(GameState.PAUSED);
            showAbilitySelectionDialog();
        }


        xpProgressBar.setRange(0, xpForNextLevel);
        xpProgressBar.setValue(currentXP);
        levelLabel.setText("Level: " + level);

        if (Game.getCurrentPlayer().getPlayerHealth() <= 0) {
            Game.setGameState(GameState.GAME_OVER);
        }

        if (timeElapsed >= WIN_TIME) {
            Game.setGameState(GameState.WINNING);
        }
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    public void showAbilitySelectionDialog() {
        isAbilitySelectionActive = true;

        List<Ability> abilities = new ArrayList<>(List.of(Ability.values()));
        Collections.shuffle(abilities);
        randomAbilities = abilities.subList(0, 3);

        abilityDialog = new Dialog("Choose Your Ability\n", skin) {
            protected void result(Object object) {
                if (object instanceof Ability) {
                    Ability chosen = (Ability) object;
                    applyAbility(chosen);
                    isAbilitySelectionActive = false;
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
        if (Game.isSfx_enabled()) GameAssetManager.getGameAssetManager().getAbilitySound().play();
        Player player = Game.getCurrentPlayer();
        switch (ability) {
            case VITALITY:
                vitalityImage.setColor(1, 1, 1, 1);
                player.addAbility(Ability.VITALITY);
                player.increaseMaxHp(20);
                break;
            case DAMAGER :
                damagerImage.setColor(1, 1, 1, 1);
                player.addAbility(Ability.DAMAGER);
                player.getEquippedWeapon().increaseDamage();
                controller.getWeaponController().startIncrease();
                break;
            case PROCREASE :
                procreaseImage.setColor(1, 1, 1, 1);
                player.addAbility(Ability.PROCREASE);
                player.getEquippedWeapon().increaseProjectilePerShot(1);
                break;
            case AMOCREASE :
                amocreaseImage.setColor(1, 1, 1, 1);
                player.addAbility(Ability.AMOCREASE);
                player.getEquippedWeapon().increaseMaxAmmo(5);
                break;
            case SPEEDY :
                speedyImage.setColor(1, 1, 1, 1);
                player.addAbility(Ability.SPEEDY);
                player.activateSpeedBoost(10);
                controller.getPlayerController().startBoost();
                break;
        }
    }

    public void removeBulletIconOnShoot() {
        int childrenCount = bulletTable.getChildren().size;
        if (childrenCount > 0) {
            bulletTable.removeActorAt(childrenCount - 1, true); // حذف آخرین آیکون
        }
    }

    public void refillBulletIcons(int ammoCount) {
        bulletTable.clear();
        for (int i = 0; i < ammoCount; i++) {
            Image bulletIcon = new Image(new TextureRegionDrawable(new TextureRegion(GameAssetManager.getGameAssetManager().getSmgReload_idle0_tex())));
            bulletIcon.setSize(16, 16);
            bulletTable.add(bulletIcon).pad(2);
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
            if (Game.isSfx_enabled()) GameAssetManager.getGameAssetManager().getReloadingSound().play();
            removeBulletIconOnShoot();
            refillBulletIcons(Game.getCurrentPlayer().getEquippedWeapon().getAmmo());
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
