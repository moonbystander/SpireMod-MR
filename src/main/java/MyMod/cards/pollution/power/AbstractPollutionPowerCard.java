package MyMod.cards.pollution.power;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AbstractPollutionPowerCard extends AbstractMRCard {


    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("bad3");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public static final String prex="bad3_";

    public AbstractPollutionPowerCard(){
        super("ee", NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.POWER, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.COMMON, CardTarget.NONE);
    }

    public AbstractPollutionPowerCard(String id , CardRarity rarity){
        super(prex+id, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.POWER, mountainsRiver.Enums.MR_CARD_COLOR , rarity, CardTarget.NONE);
    }

    public AbstractPollutionPowerCard(String id){
        super(prex+id, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.POWER, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.COMMON, CardTarget.NONE);
    }


    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //将卡牌添加到卡组(实际生效的是 效果 里的这句       AbstractDungeon.getCurrRoom().souls.obtain(this.card, true);
        //添加一张寄生到卡组
        AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(CardLibrary.getCard("Parasite").makeCopy()));

    }
}
