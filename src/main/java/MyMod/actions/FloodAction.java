package MyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class FloodAction extends AbstractGameAction {

    private int used=0;
    public DamageInfo info;
    //传入敏捷使用量
    public FloodAction(int used, AbstractMonster target, DamageInfo info){
        this.used=used;
        this.target=target;
        this.info = info;
    }


    @Override
    public void update() {
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,used/2),used/2));
        for (int i = 0; i < used/2; i++) {
            this.addToTop(new DamageAction(target,info,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        this.isDone = true;
    }
}
