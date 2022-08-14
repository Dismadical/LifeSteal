package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object ClearCommand {

    @Command(
        name = "",
        desc = "Clear Command",
        usage = "[player]"
    )
    @Require("lifesteal.clear")
    fun onCommand(@Sender sender: CommandSender, @OptArg() target: Player?) {

        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage(CC.translate("&cCannot do that."))
                return
            }

            val player: Player = sender
            player.inventory.setArmorContents(null)
            player.inventory.clear()
            player.sendMessage(CC.translate("&cYour inventory has been cleared."))
            return
        }

        target.inventory.setArmorContents(null)
        target.inventory.clear()
        target.sendMessage(CC.translate("&cYour inventory has been cleared."))
        sender.sendMessage(CC.translate("&c" + target.name + "'s inventory has been cleared."))

    }

}