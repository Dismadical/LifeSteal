package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

object LifestealCheckCommand {

    @Command(name = "check", desc = "Lifesteal Check Command", usage = "<player>")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender, target : Player) {
        sender.sendMessage(CC.translate("&c&lLifesteal &7» &c" + target.name + "'s has " + LifeStealUtils.getHearts(target) + " hearts."))
    }

}