package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

object LifestealPurgeCommand {

    @Command(name = "purge", desc = "Lifesteal Purge Command", usage = "")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender) {
        LifeStealUtils.purgePlayers()
        sender.sendMessage(CC.translate("&c&lLifesteal &7» &cAll eliminated players purged."))
    }

}