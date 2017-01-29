package com.toliga.ganjacombatbot;

import com.toliga.ganjabots.core.StateScheduler;
import com.toliga.ganjacombatbot.states.StartState;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(author = "GanjaSmuggler", category = Category.COMBAT, name = "Ganja Combat Bot", description = "", version = 1.0)
public class GanjaCombatBotMain extends AbstractScript {
    public static final String VERSION = "0.1.0";

    private StateScheduler stateScheduler;
    private BotGUI botGUI;
    private boolean isStarted = false;

    @Override
    public void onStart() {
        super.onStart();
        botGUI = new BotGUI(this, "Ganja Combat Bot");
        stateScheduler = new StateScheduler(this, new StartState());
    }

    @Override
    public int onLoop() {
        if (isStarted) {
            stateScheduler.executeState();
        }
        return Calculations.random(50, 100);
    }

    @Override
    public void onExit() {
        botGUI.setVisible(false);
        botGUI.dispose();
        super.onExit();
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
