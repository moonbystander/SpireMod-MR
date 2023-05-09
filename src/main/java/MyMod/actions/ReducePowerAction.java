package MyMod.actions;

import MyMod.power.BecomeAbyssPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReducePowerAction extends AbstractGameAction {

    public String powerID;

    public ReducePowerAction(AbstractCreature target,String powerID,int reduce){
        this.target=target;
        this.amount=reduce;
        this.powerID=powerID;
    }

    @Override
    public void update() {
        this.isDone=true;

        AbstractPower p = this.target.getPower(this.powerID);
        if (p != null) {
            --p.amount;
            if (p.amount == 0) {
                this.target.powers.remove(p);
            } else {
                p.updateDescription();
            }
        }

    }
}
