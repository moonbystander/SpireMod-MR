package MyMod.modifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UseStrength extends AbstractCardModifier implements AlternateCardCostModifier {
    private int needStrength =0;

    public UseStrength(int needStrenth) {
        this.needStrength = needStrenth;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new UseStrength(needStrength);
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard) {
        return AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)?AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount:0;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i) {

        int resource=-1;
        if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
            if (AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount > 0 ){
                resource=AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            }
        }
        //资源足够：消耗应该消耗的；资源不足：全部消耗
        if (resource > 0 && needStrength >= -1){
            if (needStrength == -1 || resource < needStrength){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player, -resource), -resource));
            }else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player, -needStrength), -needStrength));
            }
            i=0;
        }
        return i;
    }
}
