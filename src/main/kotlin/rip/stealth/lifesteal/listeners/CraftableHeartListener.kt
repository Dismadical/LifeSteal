package rip.stealth.lifesteal.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.LifeStealUtils

class CraftableHeartListener: Listener {

    @EventHandler
    fun onCraft(event: CraftItemEvent) {
        if (event.inventory.result!!.isSimilar(LifeSteal.instance.craftableHeart)) {
            val player: Player = event.whoClicked as Player
            if (!LifeSteal.instance.config.getBoolean("Craftable-Hearts.Delay.Enabled")) {
                return
            }

            if (LifeSteal.instance.dataConfig.get("Craftable-Hearts." + player.uniqueId) != null && LifeSteal.instance.dataConfig.getLong("Craftable-Hearts." + player.uniqueId) > System.currentTimeMillis()) {
                event.isCancelled = true
                val mins: Int = ((LifeSteal.instance.dataConfig.getLong("Craftable-Hearts." + player.uniqueId) - System.currentTimeMillis()) / 1000 / 60).toInt()
                val seconds: Int = ((LifeSteal.instance.dataConfig.getLong("Craftable-Hearts." + player.uniqueId) - System.currentTimeMillis()) / 1000 - (mins * 60)).toInt()
                player.closeInventory()
                player.sendMessage(CC.translate("&cYou are currently on cooldown for $mins minutes and $seconds seconds."))
                return
            }

            LifeSteal.instance.dataConfig.set("Craftable-Hearts." + player.uniqueId, System.currentTimeMillis() + LifeSteal.instance.config.getInt("Craftable-Hearts.Delay.Time") * 60 * 1000)
        }

    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {

        if (event.item == null) return
        if (event.hand != EquipmentSlot.HAND) return

        val player: Player = event.player

        if (event.item!!.isSimilar(LifeSteal.instance.craftableHeart)) {
            event.isCancelled = true
            if (event.item!!.amount > 1) {
                event.item!!.amount = event.item!!.amount - 1
            } else {
                player.inventory.removeItem(event.item!!)
            }
            LifeStealUtils.addHearts(player, LifeSteal.instance.config.getInt("Craftable-Hearts.Given-Item.Hearts-Gained-On-Use"))
            player.sendMessage(CC.translate("&cYou have been given " + LifeSteal.instance.config.getInt("Craftable-Hearts.Given-Item.Hearts-Gained-On-Use") + " hearts."))
        }

    }

}