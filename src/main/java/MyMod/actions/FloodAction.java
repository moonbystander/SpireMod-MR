package MyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
        for (int i = 0; i < used/2; i++) {
            this.addToTop(new DamageAction(target,info,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

        this.isDone = true;
    }
}
