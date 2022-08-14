package rip.stealth.lifesteal.utils.menu

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class Menu : InventoryHolder {

    private var inventory: Inventory? = null

    init {}

    abstract fun getTitle(): String

    abstract fun getSlots(): Int

    abstract fun getButtons(): HashMap<Int, Button>

    fun open(player: Player) {
        inventory = Bukkit.createInventory(this, getSlots(), getTitle())
        getButtons().forEach { inventory!!.setItem(it.key, it.value.getItemStack()) }
        player.openInventory(inventory!!)
    }

    override fun getInventory(): Inventory {
        return inventory!!
    }

}