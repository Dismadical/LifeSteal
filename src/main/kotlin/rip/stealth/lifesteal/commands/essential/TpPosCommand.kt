package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object TpPosCommand {

    @Command(
        name = "",
        desc = "Teleport Position Command",
        usage = "<x> <y> <z>",
    )
    @Require("lifesteal.teleportposition")
    fun onCommand(@Sender sender: CommandSender, x: Int, y: Int, z: Int) {

        if (sender !is Player) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
            return
        }

        val player: Player = sender
        player.teleport(Location(player.world, x.toDouble(), y.toDouble(), z.toDouble()))
        player.sendMessage(CC.translate("&cTeleported to x: &6$x &cy: &6$y &cz: &6$z"))

    }

}