package rip.stealth.lifesteal.commands.essential

import com.jonahseguin.drink.annotation.Command
import com.jonahseguin.drink.annotation.Require
import com.jonahseguin.drink.annotation.Sender
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.CC

object EnchantCommand {

    @Command(
        name = "",
        desc = "Enchant Command",
        usage = "<enchantment> <level>"
    )
    @Require("lifesteal.enchant")
    fun onCommand(@Sender sender: CommandSender, enchantname: String, level: Int) {

        if (sender !is Player) {
            sender.sendMessage(CC.translate("&cYou must be a player to use this command."))
            return
        }

        val player: Player = sender
        val item = player.inventory.itemInMainHand

        if (item.type == Material.AIR) {
            player.sendMessage(CC.translate("&cYou must be holding an item to enchant."))
            return
        }

        val enchantment: Enchantment? = Enchantment.getByKey(NamespacedKey.minecraft(enchantname.toLowerCase()))

        if (enchantment == null) {
            player.sendMessage(CC.translate("&cEnchantment not found."))
            return
        }

        item.addUnsafeEnchantment(enchantment, level)

        if (level > enchantment.maxLevel) {
            player.sendMessage(CC.translate("&c&lWARNING &7» &cThe enchantment level you have selected is higher than the max level for this enchantment."))
        }

    }

}