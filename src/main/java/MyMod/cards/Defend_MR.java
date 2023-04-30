package MyMod.cards;

import MyMod.characters.mountainsRiver;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class Defend_MR extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_MR");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Defend.png";
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_DMG = 2;
    public static final String ID = "Defend_MR";

    public Defend_MR(){
        super(ID,NAME,IMG_PATH,COST,DESCRIPTION,CardType.SKILL, mountainsRiver.Enums.MR_CARD_COLOR,CardRarity.BASIC, CardTarget.SELF);
        this.tags.add(CardTags.STARTER_DEFEND);
        this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    //升级增加2防1敏
    @Override
    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(1);
        }
    }

    //每次使用获得5防1敏
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
