package rip.stealth.lifesteal.commands.lifesteal.sub

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.command.CommandSender
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.CC

object LifestealReloadCommand {

    @Command(name = "reload", desc = "Lifesteal Reload Command", usage = "")
    @Require("lifesteal.admin")
    fun onCommand(@Sender sender: CommandSender) {
        val curr : Long = System.currentTimeMillis()
        LifeSteal.instance.reloadConfig()
        LifeSteal.instance.languageFile.reload()
        val diff : Long = System.currentTimeMillis() - curr
        sender.sendMessage(CC.translate("&cReloaded in " + diff + "ms"))
    }

}