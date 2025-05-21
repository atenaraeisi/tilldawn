package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

public class PlayerController {
    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public void update(){
        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }

        handlePlayerInput();
    }


    public void handlePlayerInput(){
        float nextX = player.getPosX();
        float nextY = player.getPosY();

        if (Gdx.input.isKeyPressed(Game.getUpButton()) && Gdx.input.isKeyPressed(Game.getLeftButton())) {
            nextY += player.getSpeed();
            nextX -= player.getSpeed();
            player.getPlayerSprite().flip(true, false);
        }
        else if (Gdx.input.isKeyPressed(Game.getUpButton()) && Gdx.input.isKeyPressed(Game.getRightButton())) {
            nextY += player.getSpeed();
            nextX += player.getSpeed();
        }
        else if (Gdx.input.isKeyPressed(Game.getDownButton()) && Gdx.input.isKeyPressed(Game.getLeftButton())) {
            nextX -= player.getSpeed();
            player.getPlayerSprite().flip(true, false);
            nextY -= player.getSpeed();
        }
        else if ((Gdx.input.isKeyPressed(Game.getDownButton()) && Gdx.input.isKeyPressed(Game.getRightButton()))) {
            nextX += player.getSpeed();
            nextY -= player.getSpeed();
        }
        else if (Gdx.input.isKeyPressed(Game.getUpButton())){
            nextY += player.getSpeed();
        }
        else if (Gdx.input.isKeyPressed(Game.getRightButton())){
            nextX += player.getSpeed();
        }
        else if (Gdx.input.isKeyPressed(Game.getDownButton())){
            nextY -= player.getSpeed();
        }
        else if (Gdx.input.isKeyPressed(Game.getLeftButton())){
            nextX -= player.getSpeed();
            player.getPlayerSprite().flip(true, false);
        }

        nextX = MathUtils.clamp(nextX, 0, 3776 - player.getRect().getWidth());
        nextY = MathUtils.clamp(nextY, 0, 2688 - player.getRect().getHeight());

        player.setPosX(nextX);
        player.setPosY(nextY);

        player.getPlayerSprite().setPosition(nextX, nextY);



    }


    public void idleAnimation(){
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter1_idle_animation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
