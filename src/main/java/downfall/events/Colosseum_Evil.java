package downfall.events;


import charbosses.actions.util.CharBossMonsterGroup;
import charbosses.bosses.Defect.CharBossDefect;
import charbosses.bosses.Ironclad.CharBossIronclad;
import charbosses.bosses.Merchant.CharBossMerchant;
import charbosses.bosses.Silent.CharBossSilent;
import charbosses.bosses.Watcher.CharBossWatcher;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import downfall.downfallMod;
import slimebound.SlimeboundMod;

public class Colosseum_Evil extends AbstractImageEvent {
    public static final String ID = "downfall:Colosseum";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final EventStrings eventStrings;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
    }

    private CurScreen screen;

    public Colosseum_Evil() {
        super(NAME, DESCRIPTIONS[0], "images/events/colosseum.jpg");
        this.screen = CurScreen.PRE;

        this.imageEventText.setDialogOption(OPTIONS[4]);
    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screen) {
            case PRE:
                this.screen = CurScreen.INTRO;
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[0]);
                this.imageEventText.setDialogOption(OPTIONS[1]);  //TODO - re enable this when boss death is fixed // FIXED! and enabled
                this.imageEventText.setDialogOption(OPTIONS[2]);
                return;
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        this.screen = CurScreen.FIGHT;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[3]);
                        SlimeboundMod.logger.info("fight");
                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Colosseum Nobs");
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
                        AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        AbstractDungeon.getCurrRoom().eliteTrigger = true;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        AbstractDungeon.lastCombatMetricKey = "Colosseum Nobs";

                        return;
                    case 1:
                        this.screen = CurScreen.FIGHT;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[4]);
                        SlimeboundMod.logger.info("fight");
                        downfallMod.overrideBossDifficulty = true;
                        String s = downfallMod.possEncounterList.get(AbstractDungeon.cardRandomRng.random(downfallMod.possEncounterList.size()-1));
                        switch (s) {
                            case "downfall:CharBossIronclad":
                                AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("downfall:CharBossIronclad");
                                AbstractDungeon.lastCombatMetricKey = "downfall:CharBossIronclad";
//                                AbstractDungeon.getCurrRoom().monsters = new CharBossMonsterGroup(new AbstractMonster[]{new CharBossIronclad()});
                                break;
                            case "downfall:CharBossSilent":
                                AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("downfall:CharBossSilent");
                                AbstractDungeon.lastCombatMetricKey = "downfall:CharBossSilent";
//                                AbstractDungeon.getCurrRoom().monsters = new CharBossMonsterGroup(new AbstractMonster[]{new CharBossSilent()});
                                break;
                            case "downfall:CharBossDefect":
                                AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("downfall:CharBossDefect");
                                AbstractDungeon.lastCombatMetricKey = "downfall:CharBossDefect";
//                                AbstractDungeon.getCurrRoom().monsters = new CharBossMonsterGroup(new AbstractMonster[]{new CharBossDefect()});
                                break;
                            case "downfall:CharBossWatcher":
                                AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("downfall:CharBossWatcher");
                                AbstractDungeon.lastCombatMetricKey = "downfall:CharBossWatcher";
//                                AbstractDungeon.getCurrRoom().monsters = new CharBossMonsterGroup(new AbstractMonster[]{new CharBossWatcher()});
                                break;
                            default:
                                AbstractDungeon.getCurrRoom().monsters =  MonsterHelper.getEncounter("downfall:CharBossIronclad");
                                AbstractDungeon.lastCombatMetricKey = "downfall:CharBossIronclad";
//                                AbstractDungeon.getCurrRoom().monsters = new CharBossMonsterGroup(new AbstractMonster[]{new CharBossIronclad()});
                                break;
                        }
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
                        AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        AbstractDungeon.getCurrRoom().eliteTrigger = true;
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[3]);
                        return;
                    case 2:
                        this.screen = CurScreen.FIGHT;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        return;
                    default:
                        return;
                }
            case FIGHT:
                switch (buttonPressed) {
                    case 0:
                        if (AbstractDungeon.getCurrRoom().eliteTrigger){
                            this.enterCombatFromImage();
                            break;
                        } else {
                            this.openMap();
                        }
                }
                break;
            default:
                this.openMap();
        }

    }

    private enum CurScreen {
        PRE,
        INTRO,
        FIGHT,
        RESULT;

        CurScreen() {
        }
    }
}
