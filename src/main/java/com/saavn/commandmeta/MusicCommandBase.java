package com.saavn.commandmeta;

import com.saavn.audio.GuildMusicManager;
import com.saavn.audio.PlayerManager;
import com.saavn.commandmeta.CommandBase;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;

// Base command class for music commands
abstract public class MusicCommandBase extends CommandBase {
    public MusicCommandBase(JDA jda, String name, String help, String category) {
        super(jda, name, help, category);
    }

    public MusicCommandBase(JDA jda, String name, String help, String category, Collection<OptionData> options) {
        super(jda, name, help, category, options);
    }

    abstract public void execute(SlashCommandInteractionEvent event);

    public GuildMusicManager getGuildMusicManager(Guild guild) {
        return PlayerManager.getInstance().getMusicManager(guild);
    }

    public boolean defaultChecks(SlashCommandInteractionEvent event) {
        final Member selfMember = event.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = selfMember.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            event.deferReply().setContent("I'm not playing anything currently.").queue();
            return false;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            event.deferReply().setContent("You are not connected to a voice channel.").setEphemeral(true).queue();
            return false;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            event.deferReply().setContent("You are not connected to the same voice channel as me.").setEphemeral(true).queue();
            return false;
        }

        return true;
    }
}
