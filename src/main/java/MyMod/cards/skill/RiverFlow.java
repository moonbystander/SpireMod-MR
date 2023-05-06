package MyMod.cards.skill;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import MyMod.modifiers.UseDexterity;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RiverFlow extends AbstractMRCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RiverFlow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "RiverFlow";
    public static final int needD=2;

    public RiverFlow(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.SKILL, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.UNCOMMON, CardTarget.NONE);

        this.baseMagicNumber=2;
        this.magicNumber=this.baseMagicNumber;
    }

    //费用降低为1
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        if ((this.target != AbstractCard.CardTarget.ENEMY && this.target != AbstractCard.CardTarget.SELF_AND_ENEMY || m == null || !m.isDying) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            return AbstractDungeon.player.hasPower(DexterityPower.POWER_ID) && AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount >= needD;
        } else {
            this.cantUseMessage = null;
            return false;
        }
    }

    //抽+弃
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //抽卡动作和弃牌动作
        CardModifierManager.addModifier(this,new UseDexterity(needD));
        this.addToBot(new DrawCardAction(abstractPlayer, this.magicNumber));
        this.addToBot(new DiscardAction(abstractPlayer, abstractPlayer, 1, false));
    }
}
