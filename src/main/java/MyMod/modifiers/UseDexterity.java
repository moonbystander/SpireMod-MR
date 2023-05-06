package MyMod.modifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

public class UseDexterity extends AbstractCardModifier implements AlternateCardCostModifier {

    private int needDexterity=0;

    public UseDexterity(int needDexterity) {
        this.needDexterity=needDexterity;
    }


    public void setNeedDexterity(int needDexterity){
        this.needDexterity=needDexterity;
    }

    public int getNeedDexterity(){
        return needDexterity;
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard) {
        return AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)?AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount:0;
    }

    //i为卡牌的cost
    //返回未被消耗的资源，产生效果需要使用AbstractDungeon.actionManager.addToBottom（）
    //这里仅负责消耗敏捷
    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i) {

        int resource=-1;
        if (AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)){
            if (AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount > 0 ){
                resource=AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount;
            }
        }
        if (resource > 0 && resource >= needDexterity && needDexterity >= -1){
            if (needDexterity == -1){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,-resource),-resource));
            }else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,-needDexterity),-needDexterity));
            }
            i=0;
        }
        return i;
    }


    //确定消耗资源的时机在能量使用前还是能量使用后，默认false：优先使用能量
    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new UseDexterity(needDexterity);
    }
}
