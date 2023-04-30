package MyMod.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class mountainsRiver extends CustomPlayer {

    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String MR_SHOULDER_2 = "img/char/shoulder2.png";
    private static final String MR_SHOULDER_1 = "img/char/shoulder1.png";
    private static final String MR_CORPSE = "img/char/fallen.png";
    private static final String MR_STAND = "img/char/Seles.png";

    //各种素材
    //能量图标()
    private static final String[] ORB_TEXTURES = new String[] { "img/UI_Seles/EPanel/layer5.png", "img/UI_Seles/EPanel/layer4.png", "img/UI_Seles/EPanel/layer3.png", "img/UI_Seles/EPanel/layer2.png", "img/UI_Seles/EPanel/layer1.png", "img/UI_Seles/EPanel/layer0.png", "img/UI_Seles/EPanel/layer5d.png", "img/UI_Seles/EPanel/layer4d.png", "img/UI_Seles/EPanel/layer3d.png", "img/UI_Seles/EPanel/layer2d.png", "img/UI_Seles/EPanel/layer1d.png" };
    //能量图标()
    private static final String ORB_VFX = "img/UI_Seles/energyBlueVFX.png";

    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };

    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 80;
    private static final int MAX_HP = 100;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    public mountainsRiver(String name){
        super(name, Enums.MR_CLASS,ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        //初始化角色,参数依次为:角色平常站立时的图片，篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，布局,不知道是什么的坐标，能量
        initializeClass(MR_STAND, MR_SHOULDER_2, MR_SHOULDER_1, MR_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));

    }

    @Override
    public ArrayList<String> getStartingDeck() {
        return null;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        return null;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return null;
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return null;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return null;
    }

    @Override
    public Color getCardRenderColor() {
        return null;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return null;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return null;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return null;
    }

    @Override
    public AbstractPlayer newInstance() {
        return null;
    }

    @Override
    public String getSpireHeartText() {
        return null;
    }

    @Override
    public Color getSlashAttackColor() {
        return null;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return null;
    }


    public static class Enums {
        @SpireEnum
        public static PlayerClass MR_CLASS;
        @SpireEnum(
                name = "mountainsRiver_Player"
        )
        public static AbstractCard.CardColor MR_CARD_COLOR;
        @SpireEnum(
                name = "mountainsRiver_COLOR"
        )
        public static CardLibrary.LibraryType MR_LIBRARY;
        @SpireEnum
        public static AbstractCard.CardTags STATIC_CARD;

        public Enums() {
        }
    }

}
