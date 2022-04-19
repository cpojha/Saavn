package com.saavn.util

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class TextButtonPaginator(lines: Collection<String>, per_page: Int) {
    var currentPage: Int = 0
    var lineChunks = mutableListOf<String>()

    init {
        var chunk: String = ""
        for((idx, line) in lines.withIndex()) {
            if ((idx + 1) % per_page == 0) {
                lineChunks.add(chunk)
                chunk = ""
            }
            chunk += line
        }
        lineChunks.add(chunk)
    }

    fun paginate(event: SlashCommandInteractionEvent) {
        // Custom ID format --> USERID|GUILDID|CURRENTPAGE

        event.deferReply().setContent(lineChunks.get(currentPage)).queue()
    }

    fun getChunks() : List<String> {
        return lineChunks
    }
}