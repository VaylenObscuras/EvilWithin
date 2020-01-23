package theHexaghost.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NightmareGuise extends AbstractHexaCard {

    public final static String ID = makeID("NightmareGuise");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 14;
    private static final int UPG_BLOCK = 4;

    public NightmareGuise() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        isEthereal = true;
        cardsToPreview = new ShadowGuise();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard q = new ShadowGuise();
        if (upgraded) q.upgrade();
        atb(new MakeTempCardInHandAction(q));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
            rawDescription = UPGRADE_DESCRIPTION;
            AbstractCard q = new ShadowGuise();
            q.upgrade();
            cardsToPreview = q;
            initializeDescription();
        }
    }
}