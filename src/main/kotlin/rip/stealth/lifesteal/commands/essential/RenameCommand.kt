package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import com.jonahseguin.drink.annotation.Text
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.ItemMeta
import rip.stealth.lifesteal.utils.CC

object RenameCommand {

    @Command(
        name = "",
        desc = "Rename Command",
        usage = "<name>"
    )
    @Require("lifesteal.rename")
    fun onCommand(@Sender sender: CommandSender, @Text name: String) {

        if (sender !is Player) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
            return
        }

        val player: Player = sender
        val item = player.inventory.itemInMainHand

        if (item.type == Material.AIR) {
            player.sendMessage(CC.translate("&cYou must be holding an item to rename."))
            return
        }

        val meta: ItemMeta? = item.itemMeta
        meta?.setDisplayName(CC.translate(name))
        item.itemMeta = meta

        player.sendMessage(CC.translate("&cItem Renamed To &f'${CC.translate(name)}'"))

    }

}