package com.saavn.core;

import com.saavn.commandmeta.CommandBase;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import com.saavn.Config;
import com.saavn.core.CommandManager;
import com.saavn.core.EventListener;
import java.util.Arrays;
import java.util.EnumSet;

public class SaavnDna {
    public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS};

    public JDA jda;
    public CommandManager commandManager;

    public SaavnDna() throws LoginException {
        JDA jda = JDABuilder.createDefault(Config.Companion.get("discord_bot_token"), Arrays.asList(INTENTS))
                .disableCache(EnumSet.of(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS))
                .enableCache(CacheFlag.VOICE_STATE)
                .setActivity(Activity.listening("music | /help"))
                .addEventListeners(new EventListener(this))
                .build();

        this.setJDA(jda);

        this.commandManager = new CommandManager(this, this.getJDA());

        for (CommandBase cmd : this.commandManager.getCommands()) {
            if (cmd.options == null || cmd.options.size() == 0) {
                jda.upsertCommand(cmd.getName(), cmd.getHelp()).queue();
            } else {
                jda.upsertCommand(cmd.getName(), cmd.getHelp()).addOptions(cmd.options).queue();
            }
        }
    }

    public void setJDA(JDA jda) {
        this.jda = jda;
    }

    public JDA getJDA() {
        return this.jda;
    }
}
