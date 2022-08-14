package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object GmsCommand {

    @Command(
        name = "",
        desc = "Set Gamemode To Survival",
        usage = "[player]"
    )
    @Require("lifesteal.gms")
    fun onCommand(@Sender sender: CommandSender, @OptArg target: Player?) {

        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage("You must be a player to use this command.")
                return
            }

            val player: Player = sender as Player

            player.gameMode = GameMode.SURVIVAL
            player.sendMessage(CC.translate("&cYou are now in Survival."))
            return

        }

        target.gameMode = GameMode.SURVIVAL
        target.sendMessage(CC.translate("&cYou have been set to survival."))
        sender.sendMessage(CC.translate("&cYou have set ${target.name}'s gamemode to survival."))

    }

}