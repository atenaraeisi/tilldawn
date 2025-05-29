package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.Menu.PreGameMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.GameView;


public class PreGameMenuView implements Screen {

    private final Stage stage;
    private final Skin skin;

    private CharacterType selectedCharacter;
    private WeaponType selectedWeapon;
    private int selectedTimeMinutes = 2;
    private ImageButton selectedCharacterButton;
    private ImageButton selectedWeaponButton;
    private Image selectedBackground;
    private Image selectedCharacterBackground = null;



    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.skin = skin;

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        // Title
        mainTable.add(new Label("Pre-Game Menu", skin, "title")).colspan(4).padBottom(70).row();

        // Characters
        mainTable.add(new Label("Choose Hero:", skin)).colspan(4).padBottom(10).row();
        mainTable.add(createCharacterSelection()).colspan(8).padBottom(50).row();

        // Weapons
        mainTable.add(new Label("Choose Weapon:", skin)).colspan(4).padBottom(10).row();
        mainTable.add(createWeaponSelection()).colspan(4).padBottom(50).row();

        // Game Duration
        mainTable.add(new Label("Choose Game Duration (min):", skin)).colspan(4).padBottom(10).row();
        mainTable.add(createTimeSelection()).colspan(4).padBottom(50).row();

        // Start Button
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedCharacter != null && selectedWeapon != null) {
                    startGame(selectedCharacter, selectedWeapon, selectedTimeMinutes);
                } else {
                    Dialog dialog = new Dialog("Error", skin);
                    dialog.text("Please select a character and a weapon.");
                    dialog.button("OK");
                    dialog.show(stage);
                }
            }
        });

        mainTable.add(startButton).colspan(4).padTop(20);

        stage.addActor(mainTable);
    }

    private Table createCharacterSelection() {
        Table table = new Table();
        for (CharacterType character : CharacterType.values()) {
            TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(character.getPlayerTexture()));
            drawable.setMinSize(60, 70);

            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = drawable;
            style.imageDown = drawable;
            style.imageChecked = new TextureRegionDrawable(drawable.getRegion());
            style.imageChecked.setMinWidth(60);
            style.imageChecked.setMinHeight(70);

            ImageButton button = new ImageButton(style);
            button.setSize(100, 100);

            Image background = new Image(new Texture("skin/icon.jpg"));
            background.setVisible(false);

            Stack stack = new Stack();
            stack.add(background);
            stack.add(button);

            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    selectedCharacter = character;

                    if (selectedCharacterButton != null) {
                        selectedCharacterButton.setChecked(false);
                    }
                    if (selectedCharacterBackground != null) {
                        selectedCharacterBackground.setVisible(false);
                    }

                    selectedCharacterButton = button;
                    selectedCharacterBackground = background;

                    button.setChecked(true);
                    background.setVisible(true);
                }
            });

            table.add(stack).size(100).pad(5);
        }
        return table;
    }



    private Table createWeaponSelection() {
        Table table = new Table();

        for (WeaponType weapon : WeaponType.values()) {
            TextureRegionDrawable weaponDrawable = new TextureRegionDrawable(new TextureRegion(weapon.getGunTexture()));
            weaponDrawable.setMinSize(60, 70);

            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.imageUp = weaponDrawable;
            style.imageDown = weaponDrawable;
            style.imageChecked = weaponDrawable;
            style.imageChecked.setMinWidth(60);
            style.imageChecked.setMinHeight(70);

            ImageButton button = new ImageButton(style);

            Image background = new Image(new Texture("skin/icon.jpg"));
            background.setVisible(false);

            Stack stack = new Stack();
            stack.add(background);
            stack.add(button);

            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    selectedWeapon = weapon;

                    if (selectedWeaponButton != null) {
                        selectedWeaponButton.setChecked(false);
                        selectedBackground.setVisible(false);
                    }

                    selectedWeaponButton = button;
                    selectedBackground = background;
                    background.setVisible(true);
                    button.setChecked(true);
                }
            });

            table.add(stack).size(64).pad(5);
        }

        return table;
    }



    private Table createTimeSelection() {
        Table table = new Table();
        int[] options = {2, 5, 10, 20};
        for (int time : options) {
            TextButton timeButton = new TextButton(time + " min", skin);
            timeButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    selectedTimeMinutes = time;
                }
            });
            table.add(timeButton).pad(5);
        }
        return table;
    }

    private void startGame(CharacterType character, WeaponType weapon, int duration) {
        Main.getMain().getScreen().dispose();
        Game.setGameState(GameState.PLAYING);
        Game.setCurrentPlayer(new Player(Game.getCurrentUser(), character));
        Game.getCurrentPlayer().setEquippedWeapon(new Weapon(weapon));
        GameController.setTotalGameTime(0f);
        GameController.setWinTime(duration * 60);
        Main.getMain().setScreen(new GameView(new GameController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }

}
