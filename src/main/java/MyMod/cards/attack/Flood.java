package MyMod.cards.attack;

import MyMod.actions.FloodAction;
import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import MyMod.modifiers.UseDexterity;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class Flood extends AbstractMRCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flood");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    //    public static final String IMG_PATH = "img/cards/Strike.png";
    private static final int COST = 2;
    private static final int ATTACK_DMG = 1;
    public static final String ID = "Flood";

    public static int testnum=1;

    public Flood(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.ATTACK, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.RARE, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage=ATTACK_DMG;
    }




    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        UseDexterity ud=new UseDexterity(-1);
        CardModifierManager.addModifier(this,ud);

        this.addToBot(new FloodAction(ud.getAlternateResource(this),
                abstractMonster,
                new DamageInfo(abstractPlayer, this.damage, NORMAL)
        ));

    }
}
