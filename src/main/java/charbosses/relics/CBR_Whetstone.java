package charbosses.relics;

import charbosses.bosses.AbstractCharBoss;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Whetstone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CBR_Whetstone extends AbstractCharbossRelic {
    public static final String ID = "Whetstone";

    public CBR_Whetstone() {
        super(new Whetstone());
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }

    @Override
    public void onEquip() {
        final ArrayList<AbstractCard> upgradableCards = new ArrayList<AbstractCard>();
        for (final AbstractCard c : AbstractCharBoss.boss.masterDeck.group) {
            if (c.canUpgrade() && c.type == AbstractCard.CardType.ATTACK) {
                upgradableCards.add(c);
            }
        }
        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.monsterRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            if (upgradableCards.size() == 1) {
                upgradableCards.get(0).upgrade();
            } else {
                upgradableCards.get(0).upgrade();
                upgradableCards.get(1).upgrade();
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_Whetstone();
    }
}
