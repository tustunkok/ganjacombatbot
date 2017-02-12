package com.toliga.ganjacombatbot.drawables;

import com.toliga.ganjabots.core.Utilities;
import com.toliga.ganjabots.graphics.Drawable;
import com.toliga.ganjacombatbot.GlobalSettings;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class HitpointDrawable implements Drawable {

    private AbstractScript context;
    private Image image;

    public HitpointDrawable(AbstractScript context) {
        this.context = context;
        image = Utilities.LoadImage("http://runetrack.com/images/skill_icons/constitution.gif", 15, 15);
    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(image, 320, 350, null);

        graphics.drawString("           XP/hr:", 280, 382);
        graphics.drawString("       XP gained:", 280, 399);
        graphics.drawString("Time to level up:", 280, 416);
        graphics.drawString("   Current Level:", 280, 433);

        if (GlobalSettings.BURY_BONES) {
            graphics.drawString("Prayer XP gained:", 280, 450);
        }

        graphics.drawString(context.getSkillTracker().getGainedExperiencePerHour(Skill.HITPOINTS) + " XP", 420, 382); // XP / hr
        graphics.drawString(context.getSkillTracker().getGainedExperience(Skill.HITPOINTS) + " XP", 420, 399); // XP gained

        long timeToLevelUp = context.getSkillTracker().getTimeToLevel(Skill.HITPOINTS);

        graphics.drawString(String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeToLevelUp),
                TimeUnit.MILLISECONDS.toMinutes(timeToLevelUp) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeToLevelUp)),
                TimeUnit.MILLISECONDS.toSeconds(timeToLevelUp) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeToLevelUp))), 420, 416);

        int realLevel = context.getSkills().getRealLevel(Skill.HITPOINTS);
        int boostedLevel = context.getSkills().getBoostedLevels(Skill.HITPOINTS);
        graphics.drawString(realLevel + " + " + (boostedLevel - realLevel), 420, 433);
    }
}