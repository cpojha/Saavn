package com.saavn.commands.music;

import com.saavn.audio.GuildMusicManager;
import com.saavn.audio.PlayerManager;
import com.saavn.commandmeta.MusicCommandBase;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import com.saavn.util.TextButtonPaginator; // Will be used in future prolly

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueCommand extends MusicCommandBase {
    public QueueCommand(JDA jda) {
        super(jda, "queue", "Displays the current queue of tracks", "Music");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.getPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            event.deferReply().setContent("The queue is empty currently.").queue();
            return;
        }

        final BlockingQueue<AudioTrack> queue = musicManager.getScheduler().queue;
        final int trackCount = Math.min(queue.size(), 15);
        StringBuilder queueText = new StringBuilder("**Current Queue**\n\n");
        final List<AudioTrack> trackList = new ArrayList<>(queue);

        for (int i = 0; i < trackCount; i++) {
            final AudioTrack track = trackList.get(i);
            final AudioTrackInfo info = track.getInfo();

            queueText.append("`[").append(String.valueOf(i + 1)).append("]` | **").append(info.title).append(" `[").append(formatTime(track.getDuration())).append("]`\n");
        }

        if (queue.size() > trackCount) {
            queueText.append("\nand **").append(String.valueOf(queue.size() - trackCount)).append("** more...");
        }

        event.deferReply().setContent(queueText.toString()).queue();
    }

    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
