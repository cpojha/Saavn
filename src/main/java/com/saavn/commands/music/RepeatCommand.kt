package com.saavn.commands.music

import com.saavn.audio.GuildMusicManager
import com.saavn.audio.PlayerManager
import com.saavn.commandmeta.CommandBase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class RepeatCommand(jda: JDA) : CommandBase(jda, "repeat", "Repeats the current track", "Music") {
    override fun execute(event: SlashCommandInteractionEvent?) {
        val selfMember: Member = event!!.guild!!.selfMember

        if (!selfMember.voiceState!!.inAudioChannel()) {
            event.deferReply().setContent("I'm not playing anything currently.").queue()
            return
        }

        val member: Member? = event.member

        if (!member!!.voiceState!!.inAudioChannel()) {
            event.deferReply().setContent("You are not connected to a voice channel.").setEphemeral(true).queue()
            return
        }

        if (!member.voiceState!!.channel!!.equals(selfMember!!.voiceState!!.channel)) {
            event.deferReply().setContent("You are not connected to the save voice channel as me.").setEphemeral(true).queue()
            return
        }

        var musicManager: GuildMusicManager = PlayerManager.getInstance().getMusicManager(event.guild)
        var nowRepeating = !musicManager.scheduler.repeatMode

        musicManager.scheduler.repeatMode = nowRepeating

        if (nowRepeating) {
            event.deferReply().setContent("Player has been set to **repeating** mode!").queue()
        } else {
            event.deferReply().setContent("Player has been set to **not repeating** mode!").queue()
        }
    }
}