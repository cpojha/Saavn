package com.saavn.commandmeta

import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.JDA

class HelpCommand(jda: JDA, var commands: Collection<CommandBase>) : CommandBase(jda, "help", "Shows the list of commands", "Info") {
    override fun execute(event: SlashCommandInteractionEvent) {
        var categories = mutableSetOf<String?>()
        for (command in commands) {
            categories.add(command.getCategory())
        }

        var helpText = ""
        for (category in categories) {
            helpText += "\n__**${category} Commands**__\n"
            for (command in commands) {
                if (command.category == category) {
                    if (command.options == null) {
                        helpText += "`${command.name}` - ${command.help}\n"
                    }
                    else {
                        helpText += "`${command.name} "
                        for (option in command.options) {
                            helpText += if (option.isRequired) {
                                "<${option.name}>"
                            } else {
                                "[${option.name}]"
                            }
                        }
                        helpText += "` - ${command.help}\n"
                    }
                }
            }
        }
//        var helpText = "```md\n"
//        for (category in categories) {
//            helpText += "\n< Saavn $category Commands >\n"
//            for (command in commands) {
//                if (command.category == category) {
//                    if (command.options == null) {
//                        helpText += "/${command.name}\n#${command.help}\n"
//                    }
//                    else {
//                        helpText += "/${command.name} "
//                        for (option in command.options) {
//                            helpText += if (option.isRequired) {
//                                "<${option.name}> "
//                            } else {
//                                "[${option.name}]"
//                            }
//                        }
//                        helpText += "\n#${command.help}\n"
//                    }
//                }
//            }
//            helpText += "-----------------------------------------------"
//        }
//        helpText += "```"
        event.deferReply().addEmbeds(MessageEmbed(
            null,
            "Saavn Help Menu",
            helpText,
            null,
            null,
            3092790,
            MessageEmbed.Thumbnail(event.guild?.selfMember?.avatarUrl, null, 120, 120),
            null,
            null,
            null,
            MessageEmbed.Footer("Made with 💖 by Scypher#9996!\nSaavn is particularly focused to deliver the best music experience over discord.", null, null),
            null,
            null)).queue()
    }
}