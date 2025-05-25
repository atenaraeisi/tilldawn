package com.tilldawn.Control.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.Character;
import com.tilldawn.View.GameView;
import com.tilldawn.View.Menu.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private Pregame pregame;


    public void setView(PreGameMenuView view) {
        this.view = view;
        this.pregame = new Pregame();
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {
            Main.getMain().getScreen().dispose();
            Game.setGameState(GameState.PLAYING);
            Game.setCurrentPlayer(new Player(Game.getCurrentUser(), Character.DASHER));
            Game.getCurrentPlayer().setEquippedWeapon(new Weapon(WeaponType.REVOLVER));
            Main.getMain().setScreen(new GameView(new GameController(), new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"))));
        }
    }

}
