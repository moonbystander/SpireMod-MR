package MyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LandslideAction extends AbstractGameAction {

    public LandslideAction(AbstractCreature target,int lossmaxhp){
        this.target=target;
        this.amount=lossmaxhp;


    }

    @Override
    public void update() {
        this.isDone=true;
        target.decreaseMaxHealth(this.amount);
    }
}
