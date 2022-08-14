package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object GmcCommand {

    @Command(
        name = "",
        desc = "Set Gamemode To Creative",
        usage = "[player]"
    )
    @Require("lifesteal.gmc")
    fun onCommand(@Sender sender: CommandSender, @OptArg target: Player?) {

        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage("You must be a player to use this command.")
                return
            }

            val player: Player = sender as Player

            player.gameMode = GameMode.CREATIVE
            player.sendMessage(CC.translate("&cYou are now in Creative."))
            return

        }

        target.gameMode = GameMode.CREATIVE
        target.sendMessage(CC.translate("&cYou have been set to creative."))
        sender.sendMessage(CC.translate("&cYou have set ${target.name}'s gamemode to creative."))

    }

}