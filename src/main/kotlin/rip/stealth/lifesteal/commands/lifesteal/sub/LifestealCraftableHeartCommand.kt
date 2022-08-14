package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

object LifestealCraftableHeartCommand {

    @Command(name = "craftableheart reset", desc = "Lifesteal Craftableheart Reset Cooldown Command", usage = "<player>")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender, target : Player) {
        LifeSteal.instance.dataConfig.set("Craftable-Hearts." + target.uniqueId, null)
        sender.sendMessage(CC.translate("&c&lLifesteal &7» &c" + target.name + "'s craftable heart cooldown has been reset."))
    }

}