package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

object LifestealGiveCommand {

    @Command(name = "give", desc = "Lifesteal Give Command", usage = "<player> <amount>")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender, target : Player, amount : Int) {
        LifeStealUtils.addHearts(target, amount)
        sender.sendMessage(CC.translate("&c&lLifesteal &7» &c" + amount + " hearts added to " + target.name + "."))
    }

}