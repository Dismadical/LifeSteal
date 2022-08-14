package rip.stealth.lifesteal.utils.menu

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object MenuListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {

        if (event.clickedInventory == null) return
        if (event.currentItem == null) return

        val player: Player = event.whoClicked as Player

        if (event.clickedInventory!!.holder is Menu) {
            event.isCancelled = true
            val menu: Menu = event.clickedInventory!!.holder as Menu
            menu.getButtons().forEach {
                if (event.slot == it.key) {
                    it.value.setClickType(event.click)
                    it.value.handleButton(player)
                }
            }
        }

    }

}