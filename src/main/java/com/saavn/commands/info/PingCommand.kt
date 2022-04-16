package com.saavn.commands.info

import com.saavn.commandmeta.CommandBase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class PingCommand(jda: JDA) : CommandBase(jda, "ping", "Shows latency of bot", "Info") {
    override fun execute(event: SlashCommandInteractionEvent) {
        this.jda.getRestPing().queue { it ->
            event.reply("Pong! **Response Time - `${it}ms`**").queue()
        }
    }
}