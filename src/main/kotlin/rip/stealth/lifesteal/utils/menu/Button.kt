package rip.stealth.lifesteal.utils.menu

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

abstract class Button {

    private var itemStack: ItemStack? = null
    private var clickAction: ClickType? = null

    init {}

    abstract fun getItemStack(): ItemStack

    abstract fun handleButton(player: Player)

    fun getClickType(): ClickType {
        return clickAction!!
    }

    fun setClickType(clickType: ClickType) {
        this.clickAction = clickType
    }

}