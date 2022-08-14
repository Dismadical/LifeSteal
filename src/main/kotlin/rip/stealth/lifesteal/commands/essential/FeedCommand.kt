package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object FeedCommand {

    @Command(
        name = "",
        desc = "Feed Command",
        usage = "[player]"
    )
    @Require("lifesteal.feed")
    fun onCommand(@Sender sender: CommandSender, @OptArg() target: Player?) {

        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage(CC.translate("&cCannot do that."))
                return
            }

            val player: Player = sender
            player.foodLevel = 20
            player.sendMessage(CC.translate("&cYou have been fed."))
            return
        }

        target.foodLevel = 20
        target.sendMessage(CC.translate("&cYou have been fed."))
        sender.sendMessage(CC.translate("&c" + target.name + " has been fed."))

    }

}