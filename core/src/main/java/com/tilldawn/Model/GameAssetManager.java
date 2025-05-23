package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final String character1_idle0 = "1/Idle_0.png";
    private final String character1_idle1 = "1/Idle_1.png";
    private final String character1_idle2 = "1/Idle_2.png";
    private final String character1_idle3 = "1/Idle_3.png";
    private final String character1_idle4 = "1/Idle_4.png";
    private final String character1_idle5 = "1/Idle_5.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Texture character1_idle3_tex = new Texture(character1_idle3);
    private final Texture character1_idle4_tex = new Texture(character1_idle4);
    private final Texture character1_idle5_tex = new Texture(character1_idle5);

    private final String tentacleEnemy_idle0 = "Sprite/BrainMonster/BrainMonster_0.png";
    private final String tentacleEnemy_idle1 = "Sprite/BrainMonster/BrainMonster_1.png";
    private final String tentacleEnemy_idle2 = "Sprite/BrainMonster/BrainMonster_2.png";
    private final String tentacleEnemy_idle3 = "Sprite/BrainMonster/BrainMonster_3.png";
    private final Texture tentacleEnemy_idle0_tex = new Texture(tentacleEnemy_idle0);
    private final Texture tentacleEnemy_idle1_tex = new Texture(tentacleEnemy_idle1);
    private final Texture tentacleEnemy_idle2_tex = new Texture(tentacleEnemy_idle2);
    private final Texture tentacleEnemy_idle3_tex = new Texture(tentacleEnemy_idle3);

    private final String treeEnemy_idle0 = "Sprite/T/T_TreeMonster_0.png";
    private final String treeEnemy_idle1 = "Sprite/T/T_TreeMonster_1.png";
    private final String treeEnemy_idle2 = "Sprite/T/T_TreeMonster_2.png";
    private final String treeEnemy_idle3 = "Sprite/T/T_TreeMonsterWalking.png";
    private final Texture treeEnemy_idle0_tex = new Texture(treeEnemy_idle0);
    private final Texture treeEnemy_idle1_tex = new Texture(treeEnemy_idle1);
    private final Texture treeEnemy_idle2_tex = new Texture(treeEnemy_idle2);
    private final Texture treeEnemy_idle3_tex = new Texture(treeEnemy_idle3);

    private final String eyeBat_idle0 = "Sprite/EyeMonster/EyeMonster_0.png";
    private final String eyeBat_idle1 = "Sprite/EyeMonster/EyeMonster_1.png";
    private final String eyeBat_idle2 = "Sprite/EyeMonster/EyeMonster_2.png";
    private final Texture eyeBat_idle0_tex = new Texture(eyeBat_idle0);
    private final Texture eyeBat_idle1_tex = new Texture(eyeBat_idle1);
    private final Texture eyeBat_idle2_tex = new Texture(eyeBat_idle2);

    private final String elderBoss_idle0 = "Sprite/ElderBrain/ElderBrain.png";
    private final String elderBoss_idle1 = "Sprite/ElderBrain/ElderBrain_Em.png";
    private final Texture elderBoss_idle0_tex = new Texture(elderBoss_idle0);
    private final Texture elderBoss_idle1_tex = new Texture(elderBoss_idle1);

    private final String shotGun_idle0 = "Sprite/T/T_Gun_Reload_0.png";
    private final String shotGun_idle1 = "Sprite/T/T_Gun_Reload_1.png";
    private final String shotGun_idle2 = "Sprite/T/T_Gun_Reload_2.png";
    private final Texture shotGun_idle0_tex = new Texture(shotGun_idle0);
    private final Texture shotGun_idle1_tex = new Texture(shotGun_idle1);
    private final Texture shotGun_idle2_tex = new Texture(shotGun_idle2);

    private Music backgroundMusic;
    private final Animation<Texture> character1_idle_frames = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);
    private final Animation<Texture> tentacleEnemy_idle_frames = new Animation<>(0.2f, tentacleEnemy_idle0_tex, tentacleEnemy_idle1_tex, tentacleEnemy_idle2_tex, tentacleEnemy_idle3_tex);
    private final Animation<Texture> treeEnemy_idle_frames = new Animation<>(0.3f, treeEnemy_idle0_tex, treeEnemy_idle1_tex, treeEnemy_idle2_tex, treeEnemy_idle3_tex);
    private final Animation<Texture> eyeBat_idle_frames = new Animation<>(0.2f, eyeBat_idle0_tex, eyeBat_idle1_tex, eyeBat_idle2_tex);
    private final Animation<Texture> elderBoss_idle_frames = new Animation<>(0.1f, elderBoss_idle0_tex, elderBoss_idle1_tex);
    private final Animation<Texture> shotGun_idle_frames = new Animation<>(0.1f, shotGun_idle0_tex, shotGun_idle1_tex, shotGun_idle2_tex);


    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String bullet = "bullet.png";


    private GameAssetManager(){
        // بارگذاری و پخش آهنگ
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/alex-productions-epic-cinematic-gaming-cyberpunk-reset(chosic.com).mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_frames;
    }

    public String getCharacter1_idle0(){
        return character1_idle0;
    }

    public String getTentacleEnemy_idle0() {
        return tentacleEnemy_idle0;
    }

    public Animation<Texture> getTentacleEnemy_idle_frames() {
        return tentacleEnemy_idle_frames;
    }

    public String getTreeEnemy_idle0() {
        return treeEnemy_idle0;
    }

    public Animation<Texture> getTreeEnemy_idle_frames() {
        return treeEnemy_idle_frames;
    }

    public String getCharacter1_idle1() {
        return character1_idle1;
    }

    public Texture getTentacleEnemy_idle0_tex() {
        return tentacleEnemy_idle0_tex;
    }

    public Texture getTreeEnemy_idle0_tex() {
        return treeEnemy_idle0_tex;
    }

    public Texture getEyeBat_idle0_tex() {
        return eyeBat_idle0_tex;
    }

    public Texture getElderBoss_idle0_tex() {
        return elderBoss_idle0_tex;
    }

    public Animation<Texture> getElderBoss_idle_frames() {
        return elderBoss_idle_frames;
    }

    public Animation<Texture> getEyeBat_idle_frames() {
        return eyeBat_idle_frames;
    }

    public Animation<Texture> getShotGun_idle_frames() {
        return shotGun_idle_frames;
    }

    public Texture getShotGun_idle0_tex() {
        return shotGun_idle0_tex;
    }

    public Texture getSmgTexture(){
        return smgTexture;
    }

    public String getSmg(){
        return smg;
    }

    public String getBullet(){
        return bullet;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }


    public void changeMusic(String path, float volume) {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(volume);
        backgroundMusic.play();
    }

}
