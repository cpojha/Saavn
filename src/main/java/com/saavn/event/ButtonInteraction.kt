package com.saavn.event

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import com.saavn.audio.PlayerManager
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class ButtonInteraction(event: ButtonInteractionEvent) {
    init {
        if (event.componentId.startsWith("MUSIC:")) {
            var songName = "ytsearch:" + event.componentId.replace("MUSIC:", "");

            PlayerManager.getInstance().audioPlayerManager.loadItem(songName, object: AudioLoadResultHandler {
                var musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                override fun loadFailed(exception: FriendlyException?) {
                    event.deferReply().setContent("Unable to load that track.").queue()
                    return
                }

                override fun noMatches() {
                    event.deferReply().setContent("Something went wrong while finding a matching track.").queue()
                    return
                }

                override fun playlistLoaded(playlist: AudioPlaylist?) {
                    val track = playlist?.tracks?.get(0)
                    event.deferReply().setContent("**Added to queue**: " + track?.info?.title + " - **Requested by:** " + event.member!!.asMention).queue()
                    musicManager.scheduler.queue(track)
                }

                override fun trackLoaded(track: AudioTrack?) {
                    event.deferReply().setContent("**Added to queue**: " + track?.info?.title + " - **Requested by:** " + event.member!!.asMention).queue()
                    musicManager.scheduler.queue(track)
                }
            })
        }
    }
}