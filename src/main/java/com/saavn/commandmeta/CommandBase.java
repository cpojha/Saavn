package com.saavn.commandmeta;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;
import java.util.List;

public abstract class CommandBase {
    public JDA jda;
    public String category;
    public String help;
    public String name;
    public Collection<OptionData> options;

    public CommandBase(JDA jda, String name, String help, String category, Collection<OptionData> options) {
        this.jda = jda;
        this.name = name;
        this.help = help;
        this.category = category;
        this.options = options;
    }

    public CommandBase(JDA jda, String name, String help, String category) {
        this.jda = jda;
        this.name = name;
        this.help = help;
        this.category = category;
    }

    public void setCategory(String categoryName) {
        this.category = categoryName;
    }

    public String getCategory() {
        return this.category;
    }

    public abstract void execute(SlashCommandInteractionEvent event);

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHelp() {
        return this.help;
    }

    public String getName() {
        return this.name;
    }
}
