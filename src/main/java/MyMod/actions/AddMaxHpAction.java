package MyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class AddMaxHpAction extends AbstractGameAction {

    public AddMaxHpAction(AbstractCreature target,int amount){
        this.target=target;
        this.amount=amount;
    }

    @Override
    public void update() {
        this.isDone=true;
        target.increaseMaxHp(amount,true);
    }
}
