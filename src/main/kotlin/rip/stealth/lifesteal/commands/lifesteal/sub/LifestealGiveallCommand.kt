package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

object LifestealGiveallCommand {

    @Command(name = "giveall", desc = "Lifesteal Giveall Command", usage = "<amount>")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender, amount : Int) {
        Bukkit.getOnlinePlayers().forEach { LifeStealUtils.addHearts(it, amount) }
        sender.sendMessage(CC.translate("&c&lLifesteal &7» &c$amount hearts given to all players."))
    }

}