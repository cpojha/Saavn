package com.saavn.core

import com.saavn.core.SaavnDna
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import com.saavn.commandmeta.CommandBase
import net.dv8tion.jda.api.JDA
import org.jetbrains.annotations.Nullable

class CommandManager(bot: SaavnDna, jda: JDA) {
    var commands: MutableList<CommandBase> = ArrayList<CommandBase>();

    init {
        // Add commands here
    }

    fun addCommand(cmd: CommandBase) {
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