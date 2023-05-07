package MyMod.cards.attack;

import MyMod.actions.LandslideAction;
import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Landslide extends AbstractMRCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Landslide");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public static final String ID = "Landslide";




    public Landslide(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.SKILL, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber=20;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new LandslideAction(abstractPlayer,this.magicNumber));

    }
}
