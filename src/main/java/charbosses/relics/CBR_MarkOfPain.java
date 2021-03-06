package charbosses.relics;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import charbosses.cards.status.EnWound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MarkOfPain;

import java.util.ArrayList;

public class CBR_MarkOfPain extends AbstractCharbossRelic {
    public static final String ID = "MarkOfPain";

    public CBR_MarkOfPain() {
        super(new MarkOfPain());
    }

    @Override
    public String getUpdatedDescription() {
        if (AbstractCharBoss.boss != null) {
            return this.setDescription(AbstractCharBoss.boss.chosenClass);
        }
        return this.setDescription(null);
    }

    private String setDescription(final AbstractPlayer.PlayerClass c) {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void updateDescription(final AbstractPlayer.PlayerClass c) {
        this.description = this.setDescription(c);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> groupToModify, int actIndex) {
        this.owner.chosenArchetype.addSpecificCard("Mark of Pain", new EnWound());
        this.owner.chosenArchetype.addSpecificCard("Mark of Pain", new EnWound());
    }

    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractCharBoss.boss.energy;
        ++energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractCharBoss.boss.energy;
        --energy.energyMaster;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new CBR_MarkOfPain();
    }
}
