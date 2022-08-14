package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object TpCommand {

    @Command(
        name = "",
        desc = "Teleport Command",
        usage = "<player>>"
    )
    @Require("lifesteal.teleport")
    fun onCommand(@Sender sender: CommandSender, target: Player) {

        if (sender !is Player) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
            return
        }

        val player: Player = sender
        player.teleport(target)
        player.sendMessage(CC.translate("&cTeleported to &6${target.name}&c."))

    }

}