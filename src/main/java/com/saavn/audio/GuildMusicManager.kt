package com.saavn.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager

class GuildMusicManager(manager: AudioPlayerManager) {
    var player: AudioPlayer
    var scheduler: TrackScheduler

    init {
        this.player = manager.createPlayer()
        this.scheduler = TrackScheduler(player)
        player.addListener(scheduler)
    }

    fun getSendHandler() : AudioPlayerSendHandler {
        return AudioPlayerSendHandler(player);
    }
}