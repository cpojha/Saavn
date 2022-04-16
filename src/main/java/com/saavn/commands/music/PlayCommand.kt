package com.saavn.commands.music

import com.saavn.audio.PlayerManager
import com.saavn.commandmeta.CommandBase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.AudioChannel
import net.dv8tion.jda.api.entities.GuildVoiceState
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import java.net.URI
import java.net.URISyntaxException

class PlayCommand(jda: JDA) :
    CommandBase(jda,
        "play",
        "Play music from a given URL or search for a track",
        "Music",
        arrayOf(OptionData(OptionType.STRING, "input", "A search term or a link", true))) {

    fun isUrl(url: String) : Boolean {
        try {
            URI(url);
            return true;
        } catch (e: URISyntaxException) {
            return false;
        }
    }

    override fun execute(event: SlashCommandInteractionEvent?) {
        var selfMember: Member = event!!.guild!!.selfMember
        var selfVoiceState: GuildVoiceState? = selfMember.voiceState

        var mem: Member? = event.member
        var memVoiceState: GuildVoiceState? = mem!!.voiceState

        if (!memVoiceState!!.inAudioChannel()) {
            event.deferReply().setContent("You need to be in a voice channel to use this command.").queue()
            return
        }

        if (!selfVoiceState!!.inAudioChannel() || !memVoiceState.channel!!.equals(selfVoiceState.channel)) {
            val audioManager = event!!.guild!!.audioManager
            val voiceChannel: AudioChannel? = mem!!.getVoiceState()!!.getChannel()

            audioManager.openAudioConnection(voiceChannel)
        }

        var link: String = event.getOption("input")!!.asString

        if (!isUrl(link)) {
            link = "ytsearch:$link"
        }

        PlayerManager.getInstance().loadAndPlay(event, link)
    }
}