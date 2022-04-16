package com.saavn.commands.music;

import com.saavn.commandmeta.CommandBase;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.entities.AudioChannel;

public class JoinCommand extends CommandBase {
    public JoinCommand(JDA jda) {
        super(jda, "join", "Connect bot to a voice channel", "Music");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        final Member me = event.getGuild().getSelfMember();
        final GuildVoiceState meVoiceState = me.getVoiceState();

        if(meVoiceState.inAudioChannel()) {
            event.deferReply().setContent("I'm already connected to a voice channel.").queue();
            return;
        }

        final Member mem = event.getMember();
        final GuildVoiceState memVoiceState = mem.getVoiceState();

        if(memVoiceState.getChannel() == null) {
            event.deferReply().setContent("You need to be in a voice channel first.").queue();
            return;
        }

        final AudioManager audioManager = event.getGuild().getAudioManager();
        final AudioChannel voiceChannel = mem.getVoiceState().getChannel();

        audioManager.openAudioConnection(voiceChannel);
        event.deferReply().setContent("Successfully connected to <#" + voiceChannel.getId() + ">!").queue();
    }
}
