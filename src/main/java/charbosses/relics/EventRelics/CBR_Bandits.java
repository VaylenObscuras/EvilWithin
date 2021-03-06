package charbosses.relics.EventRelics;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import charbosses.relics.AbstractCharbossRelic;
import charbosses.relics.CBR_RedMask;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import downfall.downfallMod;

import java.util.ArrayList;


public class CBR_Bandits extends AbstractCharbossRelic {
    public static String ID = downfallMod.makeID("Bandits");
    private static RelicTier tier = RelicTier.SPECIAL;
    private static LandingSound sound = LandingSound.MAGICAL;
    private String addedName;

    public CBR_Bandits() {
        super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/bandits.png")));
        this.largeImg = null;
    }

    @Override
    public void modifyCardsOnCollect(ArrayList<AbstractBossCard> list, int actIndex) {
                AbstractCharBoss.boss.chosenArchetype.addSpecificRelic(new CBR_RedMask(),AbstractCharBoss.boss,"Golden Idol Event", list);
                this.owner.damage(new DamageInfo(this.owner, MathUtils.floor(this.owner.maxHealth * 0.05F), DamageInfo.DamageType.HP_LOSS));

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CBR_Bandits();
    }
}
