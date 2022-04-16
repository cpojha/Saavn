package com.saavn.commandmeta;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteraction;

public abstract class CommandBase {
    public JDA jda;
    public String category;
    public String help;
    public String name;

    public CommandBase(JDA jda) {
        this.jda = jda;
        this.name = name;
        this.help = help;
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
