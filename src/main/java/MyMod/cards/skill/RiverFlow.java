package MyMod.cards.skill;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import MyMod.modifiers.UseDexterity;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RiverFlow extends AbstractMRCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RiverFlow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "RiverFlow";

    public RiverFlow(String name){
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

    //抽+弃
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //抽卡动作和弃牌动作
        AbstractGameAction[] actions=new AbstractGameAction[]{new DrawCardAction(abstractPlayer, this.magicNumber),
                new DiscardAction(abstractPlayer, abstractPlayer, 1, false)};
        CardModifierManager.addModifier(this,new UseDexterity(2,actions));
    }
}
