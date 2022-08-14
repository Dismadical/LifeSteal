package rip.stealth.lifesteal.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import rip.stealth.lifesteal.LifeSteal
import rip.stealth.lifesteal.utils.LifeStealUtils

class JoinListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {

        if (LifeStealUtils.getHearts(event.player) == 0) {
            LifeStealUtils.createProfile(event.player)
        }

        if (LifeSteal.instance.dataConfig.get("Eliminated." + event.player.name) != null) {
            LifeSteal.instance.dataConfig.set("Eliminated." + event.player.name, null)
        }

        event.player.maxHealth = (LifeStealUtils.getHearts(event.player) * 2).toDouble()
        event.player.health = event.player.maxHealth

    }

}