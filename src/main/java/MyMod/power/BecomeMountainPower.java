package MyMod.power;


import MyMod.actions.ReducePowerAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;


//回合开始时添加getD点敏捷
public class BecomeMountainPower extends AbstractPower {
    // 能力的ID
    public static String POWER_ID = "BecomeMountainPower";
    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;





    public BecomeMountainPower(AbstractCreature owner,int amount){
        this.amount=amount;

        this.name = NAME;
        this.ID = BecomeMountainPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        String path128 = "img/powers/text84.png";
        String path48 = "img/powers/text32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        this.flash();

        this.addToBot(new ReducePowerAction(this.owner,BecomeMountainPower.POWER_ID,1));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player,2 ),2));

    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
}
