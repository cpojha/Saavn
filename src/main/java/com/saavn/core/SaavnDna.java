package com.saavn.core;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;
import com.saavn.Config;

import java.util.Arrays;

public class SaavnDna {
    public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_VOICE_STATES};

    public JDA jda;

    public SaavnDna() throws LoginException {
        JDA jda = JDABuilder.createLight(Config.Companion.get("discord_bot_token"), Arrays.asList(INTENTS))
                .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.listening("music | s!help"))
                .build();

        this.setJDA(jda);
    }

    public void setJDA(JDA jda) {
        this.jda = jda;
    }

    public JDA getJDA() {
        return this.jda;
    }
}
