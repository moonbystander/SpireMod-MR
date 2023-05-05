package MyMod.modcore;

import MyMod.cards.skill.Defend_MR;
import MyMod.cards.attack.Strike_MR;
import MyMod.characters.mountainsRiver;
import MyMod.relics.Starter_Relic;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;


@SpireInitializer
public class MRMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    private static final String MY_CHARACTER_BUTTON = "img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "img/char/Character_Portrait.png";

    private static final String BG_ATTACK_512 = "img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "img/512/bg_skill_512.png";
    private static final String energy_orb = "img/512/SELESOrb.png";

    private static final String BG_ATTACK_1024 = "img/1024/bg_attack_1024.png";
    private static final String BG_POWER_1024 = "img/1024/bg_power_1024.png";
    private static final String BG_SKILL_1024 = "img/1024/bg_skill_1024.png";

    private static final String big_orb = "img/1024/SELESOrb.png";
    private static final String small_orb = "img/UI/energyOrb.png";
    private static final Color MY_COLOR = new Color(0.0F, 1.0F, 1.0F, 1.0F);


    public MRMod(){
        BaseMod.subscribe(this);
        BaseMod.addColor(mountainsRiver.Enums.MR_CARD_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, energy_orb, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, big_orb, small_orb);

    }

    public static void initialize() {
        new MRMod();
    }


    //注册自定义卡牌
    @Override
    public void receiveEditCards() {

        BaseMod.addCard(new Strike_MR());
        BaseMod.addCard(new Defend_MR());

        //TODO:使用 AutoAdd自动注册所有卡牌

    }

    //注册自定义角色
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new mountainsRiver(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, mountainsRiver.Enums.MR_CLASS);
    }

    //注册自定义关键字
    @Override
    public void receiveEditKeywords() {

    }

    //注册自定义遗物
    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new Starter_Relic(), mountainsRiver.Enums.MR_CARD_COLOR);
    }

    //注册文本
    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/ZHS" + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/ZHS" + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/ZHS" + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/ZHS" + "/powers.json");
    }
    public static Color GetCharColor() {
        return MY_COLOR;
    }
}
