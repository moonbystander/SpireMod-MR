package MyMod.modifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UseSandD extends AbstractCardModifier implements AlternateCardCostModifier {
    private int needStrength=0;
    private int needDexterity=0;

    public UseSandD( int needStrength,int needDexterity) {
        this.needStrength = needStrength;
        this.needDexterity = needDexterity;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new UseSandD(needStrength,needDexterity);
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard) {
        return 0;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int i) {
        if (needDexterity < -1 || needStrength < -1){
            return i;
        }
        int resourceD=-2;
        int resourceS=-2;
        if (AbstractDungeon.player.hasPower(DexterityPower.POWER_ID)){
            if (AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount > 0 ){
                resourceD=AbstractDungeon.player.getPower(DexterityPower.POWER_ID).amount;
            }
        }
        if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
            if (AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount > 0 ){
                resourceS=AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
            }
        }
        if (resourceD >= needDexterity && resourceS >= needStrength ){
            if (needStrength == -1){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new LoseStrengthPower(AbstractDungeon.player,resourceS),resourceS));
            }else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new LoseStrengthPower(AbstractDungeon.player,needStrength),needStrength));
            }
            if (needDexterity == -1){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new LoseDexterityPower(AbstractDungeon.player,resourceD),resourceD));
            }else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new LoseDexterityPower(AbstractDungeon.player,needDexterity),needDexterity));
            }
            i=0;
        }
        return i;
    }
}
