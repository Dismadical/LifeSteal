package rip.stealth.lifesteal.commands.lifesteal

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import rip.stealth.lifesteal.utils.CC

object LifestealCommand {

    @Command(name = "", desc = "Lifesteal command", usage = "", aliases = ["help"])
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender:CommandSender) {
        helpMessage.forEach { sender.sendMessage(CC.translate(it)) }
    }

    private val helpMessage:List<String> = listOf(
        "&c&lLifesteal Commands",
        "",
        "&c/lifesteal &7- &fShows this",
        "&c/lifesteal help &7- &fShows this",
        "&c/lifesteal reload &7- &fReloads the plugin",
        "&c/lifesteal check <player> &7- &fChecks a player's hearts",
        "&c/lifesteal set <player> <amount> &7- &fSets a player's hearts",
        "&c/lifesteal give <player> <amount> &7- &fGives a player hearts",
        "&c/lifesteal remove <player> <amount> &7- &fTakes hearts from a player",
        "&c/lifesteal giveall <amount> &7- &fGives all players hearts",
        "&c/lifesteal eliminate <player> &7- &fEliminates a player's hearts",
        "&c/lifesteal purge &7- &fPurges all eliminated players"
    )

}