package charbosses.relics;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RedSkull;
import com.megacrit.cardcrawl.rooms.*;

import charbosses.bosses.AbstractCharBoss;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;

public class CBR_RedSkull extends AbstractCharbossRelic
{
    private boolean isActive;
    
    public CBR_RedSkull() {
        super(new RedSkull());
        this.isActive = false;
    }
    
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 3 + this.DESCRIPTIONS[1];
    }
    
    @Override
    public void onBloodied() {
        this.flash();
        this.pulse = true;
        if (!this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            final AbstractCharBoss p = AbstractCharBoss.boss;
            this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
            this.addToTop(new RelicAboveCreatureAction(p, this));
            this.isActive = true;
            p.hand.applyPowers();
        }
    }
    
    @Override
    public void onNotBloodied() {
        if (this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
        	final AbstractCharBoss p = AbstractCharBoss.boss;
            this.addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, -3), -3));
        }
        this.stopPulse();
        this.isActive = false;
        AbstractCharBoss.boss.hand.applyPowers();
    }
    
    @Override
    public void onVictory() {
        this.pulse = false;
        this.isActive = false;
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new CBR_RedSkull();
    }
}