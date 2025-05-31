package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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

    private final String character2_idle0 = "2/Idle_0 #8329.png";
    private final String character2_idle1 = "2/Idle_1 #8359.png";
    private final String character2_idle2 = "2/Idle_2 #8818.png";
    private final String character2_idle3 = "2/Idle_3 #8456.png";
    private final String character2_idle4 = "2/Idle_4 #8317.png";
    private final String character2_idle5 = "2/Idle_5 #8306.png";
    private final Texture character2_idle0_tex = new Texture(character2_idle0);
    private final Texture character2_idle1_tex = new Texture(character2_idle1);
    private final Texture character2_idle2_tex = new Texture(character2_idle2);
    private final Texture character2_idle3_tex = new Texture(character2_idle3);
    private final Texture character2_idle4_tex = new Texture(character2_idle4);
    private final Texture character2_idle5_tex = new Texture(character2_idle5);


    private final String character3_idle0 = "3/Idle_0 #8326.png";
    private final String character3_idle1 = "3/Idle_1 #8356.png";
    private final String character3_idle2 = "3/Idle_2 #8815.png";
    private final String character3_idle3 = "3/Idle_3 #8453.png";
    private final String character3_idle4 = "3/Idle_4 #8314.png";
    private final String character3_idle5 = "3/Idle_5 #8303.png";
    private final Texture character3_idle0_tex = new Texture(character3_idle0);
    private final Texture character3_idle1_tex = new Texture(character3_idle1);
    private final Texture character3_idle2_tex = new Texture(character3_idle2);
    private final Texture character3_idle3_tex = new Texture(character3_idle3);
    private final Texture character3_idle4_tex = new Texture(character3_idle4);
    private final Texture character3_idle5_tex = new Texture(character3_idle5);


    private final String character4_idle0 = "4/Idle_0.png";
    private final String character4_idle1 = "4/Idle_1.png";
    private final String character4_idle2 = "4/Idle_2.png";
    private final String character4_idle3 = "4/Idle_3.png";
    private final String character4_idle4 = "4/Idle_4.png";
    private final String character4_idle5 = "4/Idle_5.png";
    private final Texture character4_idle0_tex = new Texture(character4_idle0);
    private final Texture character4_idle1_tex = new Texture(character4_idle1);
    private final Texture character4_idle2_tex = new Texture(character4_idle2);
    private final Texture character4_idle3_tex = new Texture(character4_idle3);
    private final Texture character4_idle4_tex = new Texture(character4_idle4);
    private final Texture character4_idle5_tex = new Texture(character4_idle5);



    private final String character5_idle0 = "5/Idle_0.png";
    private final String character5_idle1 = "5/Idle_1.png";
    private final String character5_idle2 = "5/Idle_2.png";
    private final String character5_idle3 = "5/Idle_3.png";
    private final String character5_idle4 = "5/Idle_4.png";
    private final String character5_idle5 = "5/Idle_5.png";
    private final Texture character5_idle0_tex = new Texture(character5_idle0);
    private final Texture character5_idle1_tex = new Texture(character5_idle1);
    private final Texture character5_idle2_tex = new Texture(character5_idle2);
    private final Texture character5_idle3_tex = new Texture(character5_idle3);
    private final Texture character5_idle4_tex = new Texture(character5_idle4);
    private final Texture character5_idle5_tex = new Texture(character5_idle5);

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

    private final String smgReload_idle0 = "smg/SMGReload_0.png";
    private final String smgReload_idle1 = "smg/SMGReload_1.png";
    private final String smgReload_idle2 = "smg/SMGReload_2.png";
    private final String smgReload_idle3 = "smg/SMGReload_3.png";
    private final Texture smgReload_idle0_tex = new Texture(smgReload_idle0);
    private final Texture smgReload_idle1_tex = new Texture(smgReload_idle1);
    private final Texture smgReload_idle2_tex = new Texture(smgReload_idle2);
    private final Texture smgReload_idle3_tex = new Texture(smgReload_idle2);


    private final String fireExplosion_idle0 = "Sprite/T/T_FireExplosionSmall_0.png";
    private final String fireExplosion_idle1 = "Sprite/T/T_FireExplosionSmall_1.png";
    private final String fireExplosion_idle2 = "Sprite/T/T_FireExplosionSmall_2.png";
    private final String fireExplosion_idle3 = "Sprite/T/T_FireExplosionSmall_3.png";
    private final String fireExplosion_idle4 = "Sprite/T/T_FireExplosionSmall_4.png";
    private final String fireExplosion_idle5 = "Sprite/T/T_FireExplosionSmall_5.png";
    private final Texture fireExplosion_idle0_tex = new Texture(fireExplosion_idle0);
    private final Texture fireExplosion_idle1_tex = new Texture(fireExplosion_idle1);
    private final Texture fireExplosion_idle2_tex = new Texture(fireExplosion_idle2);
    private final Texture fireExplosion_idle3_tex = new Texture(fireExplosion_idle3);
    private final Texture fireExplosion_idle4_tex = new Texture(fireExplosion_idle4);
    private final Texture fireExplosion_idle5_tex = new Texture(fireExplosion_idle5);

    private final String flash_idle0 = "Sprite/T/T_MaxCharge_0.png";
    private final String flash_idle1 = "Sprite/T/T_MaxCharge_1.png";
    private final String flash_idle2 = "Sprite/T/T_MaxCharge_2.png";
    private final String flash_idle3 = "Sprite/T/T_MaxCharge_3.png";
    private final Texture flash_idle0_tex = new Texture(flash_idle0);
    private final Texture flash_idle1_tex = new Texture(flash_idle1);
    private final Texture flash_idle2_tex = new Texture(flash_idle2);
    private final Texture flash_idle3_tex = new Texture(flash_idle3);

    private Music backgroundMusic;
    private final Animation<Texture> character1_idle_frames = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);
    private final Animation<Texture> character2_idle_frames = new Animation<>(0.1f, character2_idle0_tex, character2_idle1_tex, character2_idle2_tex, character2_idle3_tex, character2_idle4_tex, character2_idle5_tex);
    private final Animation<Texture> character3_idle_frames = new Animation<>(0.1f, character3_idle0_tex, character3_idle1_tex, character3_idle2_tex, character3_idle3_tex, character3_idle4_tex, character3_idle5_tex);
    private final Animation<Texture> character4_idle_frames = new Animation<>(0.1f, character4_idle0_tex, character4_idle1_tex, character4_idle2_tex, character4_idle3_tex, character4_idle4_tex, character4_idle5_tex);
    private final Animation<Texture> character5_idle_frames = new Animation<>(0.1f, character5_idle0_tex, character5_idle1_tex, character5_idle2_tex, character5_idle3_tex, character5_idle4_tex, character5_idle5_tex);

    private final Animation<Texture> tentacleEnemy_idle_frames = new Animation<>(0.2f, tentacleEnemy_idle0_tex, tentacleEnemy_idle1_tex, tentacleEnemy_idle2_tex, tentacleEnemy_idle3_tex);
    private final Animation<Texture> treeEnemy_idle_frames = new Animation<>(0.3f, treeEnemy_idle0_tex, treeEnemy_idle1_tex, treeEnemy_idle2_tex);
    private final Animation<Texture> eyeBat_idle_frames = new Animation<>(0.2f, eyeBat_idle0_tex, eyeBat_idle1_tex, eyeBat_idle2_tex);
    private final Animation<Texture> elderBoss_idle_frames = new Animation<>(0.1f, elderBoss_idle0_tex, elderBoss_idle1_tex);
    private final Animation<Texture> smgReload_idle_frames = new Animation<>(0.1f, smgReload_idle0_tex, smgReload_idle1_tex, smgReload_idle2_tex, smgReload_idle3_tex);
    private final Animation<Texture> fireExplosion_idle_frames = new Animation<>(0.1f, fireExplosion_idle0_tex, fireExplosion_idle1_tex, fireExplosion_idle2_tex, fireExplosion_idle3_tex, fireExplosion_idle4_tex, fireExplosion_idle5_tex);
    private final Animation<Texture> flash_idle_frames = new Animation<>(0.2f, flash_idle0_tex, flash_idle1_tex, flash_idle2_tex, flash_idle3_tex);


    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String shotGun = "smg/RevolverStill.png";
    private final Texture shotGunTexture = new Texture(shotGun);

    private final String smgDual = "smg/T_DualSMGs_Icon.png";
    private final Texture smgDualTexture = new Texture(smgDual);

    private final String vitality = "Sprite/Icon/Icon_Vitality.png";
    private final Texture vitalityTexture = new Texture(vitality);

    private final String damager = "Sprite/Icon/Icon_Miniclip.png";
    private final Texture damagerTexture = new Texture(damager);

    private final String proCrease = "Sprite/Icon/Icon_Make_It_Rain.png";
    private final Texture proCreaseTexture = new Texture(proCrease);

    private final String amoCrease = "Sprite/Icon/Icon_Recharge.png";
    private final Texture amoCreaseTexture = new Texture(amoCrease);

    private final String speedy = "Sprite/Icon/Icon_Generator.png";
    private final Texture speedyTexture = new Texture(speedy);

    private final String bullet = "bullet.png";


    //sounds
    private final Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Standard_Weapon_Whoosh_02.wav"));
    private final Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Explosion_Blood_01.wav"));
    private final Sound healthAlarmSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/sfx_lowhealth_alarmloop1.wav"));
    private final Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/mixkit-camera-shutter-click-1133.wav"));
    private final Sound wonSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/You Win (2).wav"));
    private final Sound loseSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/fail-234710.mp3"));
    private final Sound abilitySound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Buff_Positive.wav"));
    private final Sound footStepSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Footsteps_Casual_Grass_01.wav"));
    private final Sound gainPointSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Obtain_Points_01.wav"));
    private final Sound levelUpSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Special & Powerup (13).wav"));
    private final Sound ShotGunSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Weapon_Shotgun_Reload.wav"));
    private final Sound damageSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/mixkit-falling-hit-757.wav"));
    private final Sound reloadingSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/mixkit-shotgun-long-pump-1666.wav"));


    //B&W Shader
    private boolean blackAndWhiteEnabled = false;
    private ShaderProgram grayscaleShader;


    //avatars
    private final String avatar1 = "avatars/T_Abby_Portrait.png";
    private final Texture avatar1Texture = new Texture(avatar1);
    private final String avatar2 = "avatars/T_Dasher_Portrait.png";
    private final Texture avatar2Texture = new Texture(avatar2);
    private final String avatar3 = "avatars/T_Diamond_Portrait.png";
    private final Texture avatar3Texture = new Texture(avatar3);
    private final String avatar4 = "avatars/T_Hastur_Portrait.png";
    private final Texture avatar4Texture = new Texture(avatar4);
    private final String avatar5 = "avatars/T_Hina_Portrait.png";
    private final Texture avatar5Texture = new Texture(avatar5);
    private final String avatar6 = "avatars/T_Lilith_Portrait.png";
    private final Texture avatar6Texture = new Texture(avatar6);
    private final String avatar7 = "avatars/T_Luna_Portrait.png";
    private final Texture avatar7Texture = new Texture(avatar7);
    private final String avatar8 = "avatars/T_Raven_Portrait.png";
    private final Texture avatar8Texture = new Texture(avatar8);
    private final String avatar9 = "avatars/T_Scarlett_Portrait.png";
    private final Texture avatar9Texture = new Texture(avatar9);
    private final String avatar10 = "avatars/T_Shana_Portrait.png";
    private final Texture avatar10Texture = new Texture(avatar10);
    private final String avatar11 = "avatars/T_Spark_Portrait.png";
    private final Texture avatar11Texture = new Texture(avatar11);
    private final String avatar12 = "avatars/T_Yuki_Portrait.png";
    private final Texture avatar12Texture = new Texture(avatar12);
    private final TextureRegionDrawable[] avatarDrawables = new TextureRegionDrawable[] {
        new TextureRegionDrawable(new TextureRegion(avatar1Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar2Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar3Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar4Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar5Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar6Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar7Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar8Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar9Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar10Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar11Texture)),
        new TextureRegionDrawable(new TextureRegion(avatar12Texture)),
    };


    public TextureRegionDrawable[] getAvatarDrawables() {
        return avatarDrawables;
    }

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

    public void loadShaders() {
        ShaderProgram.pedantic = false;
        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/grayscale.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );

        if (!grayscaleShader.isCompiled()) {
            System.err.println("Shader compile error: " + grayscaleShader.getLog());
        }
    }

    public ShaderProgram getGrayscaleShader() {
        return grayscaleShader;
    }

    public boolean isBlackAndWhiteEnabled() {
        return blackAndWhiteEnabled;
    }

    public void toggleBlackAndWhite() {
        blackAndWhiteEnabled = !blackAndWhiteEnabled;
    }

    public Skin getSkin() {
        return skin;
    }

    public Sound getReloadingSound() {
        return reloadingSound;
    }

    public Sound getDamageSound() {
        return damageSound;
    }

    public Sound getShotGunSound() {
        return ShotGunSound;
    }

    public Sound getLevelUpSound() {
        return levelUpSound;
    }

    public Sound getGainPointSound() {
        return gainPointSound;
    }

    public Sound getFootStepSound() {
        return footStepSound;
    }

    public Sound getAbilitySound() {
        return abilitySound;
    }

    public Sound getWonSound() {
        return wonSound;
    }

    public Sound getClickSound() {
        return clickSound;
    }

    public Sound getLoseSound() {
        return loseSound;
    }

    public Sound getHealthAlarmSound() {
        return healthAlarmSound;
    }

    public Sound getExplosionSound() {
        return explosionSound;
    }

    public Sound getShootSound() {
        return shootSound;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_frames;
    }

    public Animation<Texture> getTentacleEnemy_idle_frames() {
        return tentacleEnemy_idle_frames;
    }

    public Animation<Texture> getTreeEnemy_idle_frames() {
        return treeEnemy_idle_frames;
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

    public Animation<Texture> getSmgReload_idle_frames() {
        return smgReload_idle_frames;
    }

    public Animation<Texture> getCharacter3_idle_frames() {
        return character3_idle_frames;
    }

    public Animation<Texture> getCharacter4_idle_frames() {
        return character4_idle_frames;
    }

    public Animation<Texture> getCharacter5_idle_frames() {
        return character5_idle_frames;
    }

    public Animation<Texture> getCharacter2_idle_frames() {
        return character2_idle_frames;
    }

    public Texture getCharacter1_idle0_tex() {
        return character1_idle0_tex;
    }

    public Texture getCharacter2_idle0_tex() {
        return character2_idle0_tex;
    }

    public Texture getCharacter3_idle0_tex() {
        return character3_idle0_tex;
    }

    public Texture getCharacter4_idle0_tex() {
        return character4_idle0_tex;
    }

    public Texture getCharacter5_idle0_tex() {
        return character5_idle0_tex;
    }

    public Texture getSmgReload_idle0_tex() {
        return smgReload_idle0_tex;
    }

    public Texture getFireExplosion_idle0_tex() {
        return fireExplosion_idle0_tex;
    }

    public Animation<Texture> getFireExplosion_idle_frames() {
        return fireExplosion_idle_frames;
    }

    public Texture getAmoCreaseTexture() {
        return amoCreaseTexture;
    }

    public Texture getProCreaseTexture() {
        return proCreaseTexture;
    }

    public Texture getDamagerTexture() {
        return damagerTexture;
    }

    public Texture getVitalityTexture() {
        return vitalityTexture;
    }

    public Texture getSpeedyTexture() {
        return speedyTexture;
    }

    public Texture getFlash_idle0_tex() {
        return flash_idle0_tex;
    }

    public Animation<Texture> getFlash_idle_frames() {
        return flash_idle_frames;
    }

    public Texture getSmgTexture(){
        return smgTexture;
    }

    public Texture getShotGunTexture() {
        return shotGunTexture;
    }

    public Texture getSmgDualTexture() {
        return smgDualTexture;
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
