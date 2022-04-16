package com.saavn.core;

import com.saavn.commandmeta.CommandBase;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import com.saavn.Config;
import com.saavn.core.CommandManager;
import com.saavn.core.EventListener;
import java.util.Arrays;

public class SaavnDna {
    public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES};

    public JDA jda;
    public CommandManager commandManager;

    public SaavnDna() throws LoginException {
        JDA jda = JDABuilder.createLight(Config.Companion.get("discord_bot_token"), Arrays.asList(INTENTS))
                .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.listening("music | s!help"))
                .addEventListeners(new EventListener(this))
                .build();

        this.setJDA(jda);

        this.commandManager = new CommandManager(this, this.getJDA());

        for (CommandBase cmd : this.commandManager.getCommands()) {
            jda.upsertCommand(cmd.getName(), cmd.getHelp()).queue();
        }
    }

    public void setJDA(JDA jda) {
        this.jda = jda;
    }

    public JDA getJDA() {
        return this.jda;
    }
}
