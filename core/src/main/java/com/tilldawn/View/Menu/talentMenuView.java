package com.tilldawn.View.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.Menu.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.CharacterType;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;

public class talentMenuView implements Screen {
    private Skin skin;
    private Stage stage;
    private Table table;

    public talentMenuView(Skin skin) {
        this.skin = skin;
    }

    private Table createHintMenu() {
        Table hintTable = new Table();
        hintTable.setFillParent(true);


        Label title = new Label("Hint Menu", skin.get("title", Label.LabelStyle.class));
        hintTable.add(title).padBottom(50).row();

        // بخش 1: معرفی hero ها
        Label heroTitle = new Label("Heroes:", skin);
        hintTable.add(heroTitle).center().row();
        Table heroTable = new Table();
        for (CharacterType character : CharacterType.values()) {
            Image image = new Image(new TextureRegionDrawable(new TextureRegion(character.getPlayerTexture())));
            Label label = new Label("HP: " + character.getHp() + " | SPD: " + character.getSpeed(), skin);
            Table info = new Table();
            info.add(image).size(48).row();
            info.add(label);
            heroTable.add(info).padRight(40);
        }
        hintTable.add(heroTable).padBottom(30).row();

        Label keyTitle = new Label("Controls:", skin);
        hintTable.add(keyTitle).center().row();
        Table keyTable = new Table();
        keyTable.add(new Label("Move Up: ", skin));
            keyTable.add(new Label(Input.Keys.toString(Game.getUpButton()), skin)).padRight(40);
        keyTable.add(new Label("Move Down: ", skin));
        keyTable.add(new Label(Input.Keys.toString(Game.getDownButton()), skin)).padRight(40);
        keyTable.add(new Label("Move Left: ", skin));
        keyTable.add(new Label(Input.Keys.toString(Game.getLeftButton()), skin)).padRight(40);
        keyTable.add(new Label("Move Right: ", skin));
        keyTable.add(new Label(Input.Keys.toString(Game.getRightButton()), skin)).padRight(40);
        keyTable.add(new Label("Reload: ", skin));
        keyTable.add(new Label(Input.Keys.toString(Game.getReloadButton()), skin)).padRight(40);
        hintTable.add(keyTable).padBottom(30).row();

        // بخش 3: کدهای تقلب
        Label cheatTitle = new Label("Cheat Codes:", skin);
        hintTable.add(cheatTitle).center().row();
        Table cheatTable = new Table();
        cheatTable.add(new Label("- Skip Time (reduce game timer) by BACKSLASH Key", skin)).row();
        cheatTable.add(new Label("- Level Up (play animation if needed) by L Key", skin)).row();
        cheatTable.add(new Label("- Gain HP (only if HP < max) by H key", skin)).row();
        cheatTable.add(new Label("- Increase one projectile by O key", skin)).row();
        cheatTable.add(new Label("- Boss Fight (go to boss stage) by B key", skin)).row();
        hintTable.add(cheatTable).padBottom(30).row();

        // بخش 4: Ability ها
        Label abilityTitle = new Label("Abilities:", skin);
        hintTable.add(abilityTitle).center().row();
        Table abilityTable = new Table();
        for (Ability ability : Ability.values()) {
            Image image = new Image(new TextureRegionDrawable(new TextureRegion(ability.getTexture())));
            Label label = new Label(ability.getDescription(), skin);
            Table cell = new Table();
            cell.add(image).size(32).padRight(10);
            cell.add(label);
            abilityTable.add(cell).center().row();
        }
        hintTable.add(abilityTable).padBottom(15).row();

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAssetManager.getGameAssetManager().getClickSound().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
            }
        });
        hintTable.add(backButton);

        return hintTable;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table = createHintMenu();
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
