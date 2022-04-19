package com.saavn.commands.music;

import com.saavn.audio.GuildMusicManager;
import com.saavn.commandmeta.MusicCommandBase;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class VolumeCommand extends MusicCommandBase {
    public VolumeCommand(JDA jda) {
        super(jda, "volume", "Set the volume of the player", "Music",
                List.of(new OptionData(
                        OptionType.NUMBER,
                        "value",
                        "The value to set the volume to",
                        false)));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        boolean pass = defaultChecks(event);
        if (!pass) { return; }

        final GuildMusicManager musicManager = this.getGuildMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.getPlayer();

        if (audioPlayer.getPlayingTrack() == null) {
            event.deferReply().setContent("I'm not playing anything currently.").queue();
            return;
        }

        if (event.getOption("value") == null) {
            event.deferReply().setContent("Volume of the player is: **" + audioPlayer.getVolume() + "%**").queue();
            return;
        }

        int volume = event.getOption("value").getAsInt();
        if (volume > 150 || volume < 0) {
            event.deferReply().setContent("Volume must be between 0 or 150 only.").queue();
            return;
        }

        audioPlayer.setVolume(volume);
        event.deferReply().setContent("Successfully changed player's volume to: **" + volume + "%**").queue();
    }
}
