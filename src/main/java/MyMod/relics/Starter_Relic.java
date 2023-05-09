package MyMod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Starter_Relic extends CustomRelic {
    public static final String ID = "Starter_Relic";
    private static final String IMG = "img/relics/cLanguageProgramBegin.png";
    private static final String IMG_OTL = "img/relics/outline/cLanguageProgramBegin.png";


    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public Starter_Relic() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.STARTER, AbstractRelic.LandingSound.CLINK);
    }

    //在战斗开始时触发,为玩家添加3点力量和3点敏捷
    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 20), 20));
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 20), 20));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
