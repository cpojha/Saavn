package com.saavn.commands.music

import com.saavn.audio.PlayerManager
import com.saavn.commandmeta.MusicCommandBase
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class ResumeCommand(jda: JDA): MusicCommandBase(jda, "resume", "Resumes the paused player", "Music") {
    override fun execute(event: SlashCommandInteractionEvent?) {
        val toPass = this.defaultChecks(event)
        if (!toPass) { return }

        var musicManager = PlayerManager.getInstance().getMusicManager(event?.getGuild())
        var audioPlayer = musicManager.player

        if (audioPlayer.getPlayingTrack() == null) {
            event?.deferReply()?.setContent("I'm not playing anything currently.")?.queue()
            return
        }

        if (!audioPlayer.isPaused) {
            event?.deferReply()?.setContent("Player is not paused.")?.queue()
            return
        }

        audioPlayer.isPaused = false
        event?.deferReply()?.setContent("Successfully **eresumed** the player.")?.queue()
    }
}