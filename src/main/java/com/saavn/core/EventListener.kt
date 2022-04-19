package com.saavn.core

import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import com.saavn.event.TextButtonInteraction

import com.saavn.event.SlashCommandInteraction
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent

class EventListener(bot: SaavnDna) : ListenerAdapter() {
    var bot: SaavnDna = bot

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        SlashCommandInteraction(this.bot, event)
    }

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        TextButtonInteraction(event)
    }
}