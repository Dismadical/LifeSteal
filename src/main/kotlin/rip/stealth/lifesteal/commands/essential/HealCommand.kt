package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import com.jonahseguin.drink.parametric.DrinkProvider
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.CC

object HealCommand {

    @Command(
        name = "",
        desc = "Heal Command",
        usage = "[player]"
    )
    @Require("lifesteal.heal")
    fun onCommand(@Sender sender: CommandSender, @OptArg() target: Player?) {

        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage(CC.translate("&cCannot do that."))
                return
            }

            val player: Player = sender
            player.health = player.maxHealth
            player.sendMessage(CC.translate("&cYou have been healed."))
            return
        }

        target.health = target.maxHealth
        target.sendMessage(CC.translate("&cYou have been healed."))
        sender.sendMessage(CC.translate("&c" + target.name + " has been healed."))

    }

}