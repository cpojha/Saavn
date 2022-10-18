package com.saavn.commands.info

import com.saavn.commandmeta.CommandBase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.entities.ApplicationInfo
import net.dv8tion.jda.api.Permission

class InviteCommand(jda: JDA) : CommandBase(jda, "invite", "Invite me in your server", "Info") {
    override fun execute(event: SlashCommandInteractionEvent) {
        var applicationInfo: ApplicationInfo = jda.retrieveApplicationInfo().complete()
        applicationInfo.setRequiredScopes("applications.commands")
        var invitePermissions = mutableListOf(Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER, Permission.MESSAGE_MANAGE, Permission.MESSAGE_SEND, Permission.VOICE_CONNECT)

        val inviteUrl = applicationInfo.getInviteUrl(invitePermissions)
        event.reply("Invite " + "**" + applicationInfo.name + "** to your server by clicking the link below!\n" + inviteUrl).queue()
    }
}