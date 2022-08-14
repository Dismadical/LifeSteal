package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.OptArg
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC
import java.util.*

object GameModeCommand {

    @Command(
        name = "",
        desc = "Change your game mode",
        usage = "<mode> [player]"
    )
    @Require("lifesteal.gamemode")
    fun onCommand(@Sender sender: CommandSender, mode: String, @OptArg target: Player?) {

        var gamemode: GameMode? = null
        var b: Boolean = false

        while (!b) {

            try {
                gamemode = GameMode.valueOf(mode.toUpperCase())
                b = true
            } catch (_: Exception) { }

            try {
                gamemode = GameMode.getByValue(mode.toInt())
                b = true
            } catch (_: Exception) { }

            try {
                gamemode = GameMode.valueOf(mode.replace("sp", "SPECTATOR").replace("s", "SURVIVAL").replace("c", "CREATIVE").replace("a", "ADVENTURE"))
                b = true
            } catch (_: Exception) { }

            b = true
        }

        if (gamemode == null) {
            sender.sendMessage(CC.translate("&cInvalid game mode."))
            return
        }



        if (target == null || !target.isOnline) {

            if (sender !is Player) {
                sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
                return
            }

            val player: Player = sender as Player

            player.gameMode = gamemode
            player.sendMessage(CC.translate("&cGamemode set to ${player.gameMode.name}."))
            return
        }

        target.gameMode = gamemode
        target.sendMessage(CC.translate("&cGamemode set to ${target.gameMode.name}."))
        sender.sendMessage(CC.translate("&cGamemode set to ${target.gameMode.name} for ${target.name}."))

    }

}