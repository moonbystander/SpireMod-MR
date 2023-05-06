package MyMod.cards.attack;

import MyMod.actions.DebrisFlowAction;
import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;

import MyMod.modifiers.UseStrength;
import basemod.helpers.CardModifierManager;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;

public class DebrisFlow extends AbstractMRCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DebrisFlow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    //    public static final String IMG_PATH = "img/cards/Strike.png";
    private static final int COST = 3;
    private static final int ATTACK_DMG = 3;
    public static final String ID = "DebrisFlow";

    public DebrisFlow(){
        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.ATTACK, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage=ATTACK_DMG;
        this.baseBlock=1;
        this.baseMagicNumber=5;
        CardModifierManager.addModifier(this,new UseStrength(this.baseMagicNumber));
    }


    @Override
    public boolean cardPlayable(AbstractMonster m){
        if ((this.target != AbstractCard.CardTarget.ENEMY && this.target != AbstractCard.CardTarget.SELF_AND_ENEMY || m == null || !m.isDying) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() ) {
            return AbstractDungeon.player.hasPower(StrengthPower.POWER_ID) && AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount >= this.baseMagicNumber;
        } else {
            this.cantUseMessage = null;
            return false;
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
            this.upgradeMagicNumber(-2);
        }
    }

    //扣除力量，丢弃所有手牌，每丢弃一张对敌人造成3点伤害并获得1点格挡
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        this.addToBot(new DebrisFlowAction(abstractMonster,new DamageInfo(abstractPlayer,
                this.damage, NORMAL),this.block));
    }
}
