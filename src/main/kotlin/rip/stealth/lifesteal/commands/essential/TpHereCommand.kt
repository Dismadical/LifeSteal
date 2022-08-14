package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object TpHereCommand {

    @Command(
        name = "",
        desc = "Teleporthere Command",
        usage = "<player>"
    )
    @Require("lifesteal.teleporthere")
    fun onCommand(@Sender sender: CommandSender, target: Player) {

        if (sender !is Player) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
            return
        }

        val player: Player = sender
        target.teleport(player)
        player.sendMessage(CC.translate("&cTeleported &6${target.name} &cto you."))

    }

}