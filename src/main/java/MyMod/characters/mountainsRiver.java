package MyMod.characters;

import MyMod.cards.skill.Defend_MR;
import MyMod.cards.attack.Strike_MR;
import MyMod.modcore.MRMod;
import MyMod.relics.Starter_Relic;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class mountainsRiver extends CustomPlayer {

    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String MR_SHOULDER_2 = "img/char/shoulder.png";
    private static final String MR_SHOULDER_1 = "img/char/shoulder.png";

    //各种素材
    //能量图标()
    private static final String[] ORB_TEXTURES = new String[] { "img/UI/EPanel/layer5.png", "img/UI/EPanel/layer4.png", "img/UI/EPanel/layer3.png", "img/UI/EPanel/layer2.png", "img/UI/EPanel/layer1.png", "img/UI/EPanel/layer0.png", "img/UI/EPanel/layer5d.png", "img/UI/EPanel/layer4d.png", "img/UI/EPanel/layer3d.png", "img/UI/EPanel/layer2d.png", "img/UI/EPanel/layer1d.png" };
    //能量图标()
    private static final String ORB_VFX = "img/UI/energyBlueVFX.png";

    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };

    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 80;
    private static final int MAX_HP = 100;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    private static final CharacterStrings characterStrings;

    static {
        characterStrings = CardCrawlGame.languagePack.getCharacterString("MR");
    }

    public mountainsRiver(String name){
        super(name, Enums.MR_CLASS,ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass("img/char/person.png", MR_SHOULDER_2, MR_SHOULDER_1, "img/char/fallen.png",
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));

    }


    //TODO:实现下列方法
    //设定初始卡组
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        int x;
        for(x = 0; x < 5; ++x) {
            retVal.add(Strike_MR.ID);
        }
        for(x = 0; x < 5; ++x) {
            retVal.add(Defend_MR.ID);
        }
        return retVal;
    }

    //设定初始遗物
    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add(Starter_Relic.ID);
        return retVal;
    }

    //设定角色选择界面的文字描述
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[0], characterStrings.TEXT[0], 65, 65, 0, 99, 5, this, this.getStartingRelics(), this.getStartingDeck(), false);
    }

    //设定进入后左上角的角色名
    @Override
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    //设定卡牌颜色
    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.MR_CARD_COLOR;
    }

    //设置不知道什么东西的颜色
    @Override
    public Color getCardRenderColor() {
        return MRMod.GetCharColor();
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_MR();
    }

    @Override
    public Color getCardTrailColor() {
        return MRMod.GetCharColor();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new mountainsRiver(this.name);
    }

    //打心脏文本
    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return MRMod.GetCharColor();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    //吸血鬼事件文本
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }


    public static class Enums {
        @SpireEnum
        public static PlayerClass MR_CLASS;
        @SpireEnum(
                name = "MR_COLOR"
        )
        public static AbstractCard.CardColor MR_CARD_COLOR;
        @SpireEnum(
                name = "MR_COLOR"
        )
        public static CardLibrary.LibraryType MR_LIBRARY;


        public Enums() {
        }
    }

}
