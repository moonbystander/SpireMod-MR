package MyMod.cards.skill;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BecomeMountain extends AbstractMRCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BecomeMountain");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public static final String ID = "BecomeMountain";
    public static final int getD=2;


    public BecomeMountain(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.SKILL, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber=2;
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

    //使用后的 M 回合 ，每回合获得 getD 点敏捷
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        this.addToBot();
    }
}
