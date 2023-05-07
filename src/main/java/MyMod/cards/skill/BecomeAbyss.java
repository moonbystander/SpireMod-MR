package MyMod.cards.skill;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import MyMod.power.BecomeAbyssPower;
import MyMod.power.BecomeMountainPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BecomeAbyss extends AbstractMRCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BecomeAbyss");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "BecomeAbyss";

    public BecomeAbyss(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.SKILL, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber=1;
        this.magicNumber=this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BecomeAbyssPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }
}
