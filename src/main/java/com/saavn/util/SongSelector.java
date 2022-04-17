package com.saavn.util;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SongSelector {
    public String content = "**Please select a track from the following:**\n";
    public List<Button> components = new ArrayList<>();

    public SongSelector(List<AudioTrack> audioTracks, long guildId) {
        int counter = 1;
        for (AudioTrack track : audioTracks) {
            if (counter >= 5) {
                break;
            }

            content += "`" + counter + "`" + " | " + track.getInfo().title + "\n";
            counter++;
        }

        components.add(Button.secondary("MUSIC:" + audioTracks.get(0).getInfo().title.substring(0, Math.min(audioTracks.get(0).getInfo().title.length(), 25)), "1"));
        components.add(Button.secondary("MUSIC:" + audioTracks.get(1).getInfo().title.substring(0, Math.min(audioTracks.get(0).getInfo().title.length(), 25)), "2"));
        components.add(Button.secondary("MUSIC:" + audioTracks.get(2).getInfo().title.substring(0, Math.min(audioTracks.get(0).getInfo().title.length(), 25)), "3"));
        components.add(Button.secondary("MUSIC:" + audioTracks.get(3).getInfo().title.substring(0, Math.min(audioTracks.get(0).getInfo().title.length(), 25)), "4"));
        components.add(Button.secondary("MUSIC:" + audioTracks.get(4).getInfo().title.substring(0, Math.min(audioTracks.get(0).getInfo().title.length(), 25)), "5"));
    }
}
