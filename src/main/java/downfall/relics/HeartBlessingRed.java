package downfall.relics;

import basemod.abstracts.CustomRelic;
import charbosses.bosses.AbstractCharBoss;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import downfall.downfallMod;

public class HeartBlessingRed extends CustomRelic {

    public static final String ID = downfallMod.makeID("HeartBlessingRed");
    private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/HeartBlessingRed.png"));
    private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/HeartBlessingRed.png"));

    public HeartBlessingRed() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void atBattleStartPreDraw() {
        if (AbstractDungeon.actNum == 3 && AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
            flash();
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractCharBoss.boss.anticard().makeCopy()));
        }
    }
}
