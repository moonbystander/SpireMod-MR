package MyMod.cards.attack;

import MyMod.cards.AbstractMRCard;
import MyMod.characters.mountainsRiver;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Strike_MR extends AbstractMRCard {



    //从.json文件中提取键名为Strike_MR的信息,并填入相应字段
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Strike_MR");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//    public static final String IMG_PATH = "img/cards/Strike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    public static final String ID = "Strike_MR";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，卡片图片,能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Strike_MR() {

        super(ID, NAME, AbstractMRCard.TESTIMG, COST, DESCRIPTION, CardType.ATTACK, mountainsRiver.Enums.MR_CARD_COLOR , CardRarity.BASIC, CardTarget.ENEMY);
        //添加基础攻击标签和将伤害设为6
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    //卡牌升级后的效果
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //更改名字和提高2点伤害1点力量
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(1);
        }
    }

    //使用卡牌时触发的动作,每次使用为玩家添加1点力量
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        //参数:动作事件(作用对象,伤害信息(来源,数值,不知道什么玩意),不知道什么玩意,不知道什么玩意)
        this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster,
                new DamageInfo(abstractPlayer,
                        this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));

        //将卡牌添加到卡组(实际生效的是 效果 里的这句       AbstractDungeon.getCurrRoom().souls.obtain(this.card, true);
        //添加一张寄生到卡组
        AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(CardLibrary.getCard("Parasite").makeCopy()));
    }

    //在CustomCard类中,已经被默认实现,这里仅作为演示进行实现,之后不再重写此方法
    //复制卡牌时触发
    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Strike_MR();
    }

}
