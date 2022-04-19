package com.saavn.core

import com.saavn.core.SaavnDna
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import com.saavn.commandmeta.CommandBase
import com.saavn.commandmeta.HelpCommand
import net.dv8tion.jda.api.JDA
import org.jetbrains.annotations.Nullable

// Importing Commands
import com.saavn.commands.info.*
import com.saavn.commands.music.*

class CommandManager(bot: SaavnDna, jda: JDA) {
    var commands: MutableList<CommandBase> = ArrayList<CommandBase>();

    init {
        this.addCommand(PingCommand(jda))
        this.addCommand(InviteCommand(jda))
        this.addCommand(JoinCommand(jda))
        this.addCommand(PlayCommand(jda))
        this.addCommand(StopCommand(jda))
        this.addCommand(SkipCommand(jda))
        this.addCommand(RepeatCommand(jda))
        this.addCommand(PauseCommand(jda))
        this.addCommand(ResumeCommand(jda))
        this.addCommand(VolumeCommand(jda))
        this.addCommand(QueueCommand(jda))

        this.addCommand(HelpCommand(jda, commands))
    }

    private fun addCommand(cmd: CommandBase) {
        this.commands.add(cmd);
    }

    @Nullable
    fun getCommand(search: String): CommandBase? {
        val searchLower = search.lowercase()

        for (cmd in this.commands) {
            if (cmd.getName().equals(searchLower)) {
                return cmd
            }
        }

        return null
    }
}