package MyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class DebrisFlowAction extends AbstractGameAction {

    public DamageInfo info;
    public int defend;

    public DebrisFlowAction(AbstractMonster target, DamageInfo info, int defend) {
        this.target=target;
        this.info = info;
        this.defend=defend;
    }

    @Override
    public void update() {

        //获取玩家手牌数
        int count = AbstractDungeon.player.hand.size();
        if (count > 0){
            //丢弃所有手牌
            this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, count, true));
            //根据弃牌数造成伤害并获得格挡
            for (int i = 0; i < count; i++) {
                this.addToTop(new DamageAction(target,info,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, defend));
            }
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,count),count));
        }
        this.isDone = true;
    }
}
