package com.saavn.event;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import com.saavn.core.SaavnDna;
import com.saavn.commandmeta.CommandBase;

public class SlashCommandInteraction {
    public SlashCommandInteraction(SaavnDna bot, SlashCommandInteractionEvent event) {
        CommandBase command = bot.commandManager.getCommand(event.getName());
        if (command != null) {
            command.execute(event);
        }
    }
}
