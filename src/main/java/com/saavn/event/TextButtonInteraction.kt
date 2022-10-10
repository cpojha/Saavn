package com.saavn.event

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import com.saavn.audio.PlayerManager
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class TextButtonInteraction(event: ButtonInteractionEvent) {
    init {
        if (event.componentId.startsWith("MUSIC:")) {
            var songName = "ytsearch:" + event.componentId.replace("MUSIC:", "");

            PlayerManager.getInstance().audioPlayerManager.loadItem(songName, object: AudioLoadResultHandler {
                var musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                override fun loadFailed(exception: FriendlyException?) {
                    event.deferEdit().setContent("Unable to load that track.").setActionRows().queue()
                    return
                }

                override fun noMatches() {
                    event.deferEdit().setContent("Something went wrong while finding a track :/").setActionRows().queue()
                    return
                }

                override fun playlistLoaded(playlist: AudioPlaylist?) {
                    val track = playlist?.tracks?.get(0)
                    event.deferEdit().setContent("**Added to queue**: " + track?.info?.title + " - **Requested by:** " + event.member!!.effectiveName).setActionRows().queue()
                    musicManager.scheduler.queue(track)
                }

                override fun trackLoaded(track: AudioTrack?) {
                    event.deferEdit().setActionRows().setContent("**Added to queue**: " + track?.info?.title + " - **Requested by:** " + event.member!!.effectiveName).queue()
                    musicManager.scheduler.queue(track)
                }
            })
        }
    }
}
