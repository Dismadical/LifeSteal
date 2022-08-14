package rip.stealth.lifesteal.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import rip.stealth.lifesteal.LifeSteal

object LifeStealUtils {

    fun createProfile(player : Player) {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) != null) {
            LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, null)
        }
        LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, LifeSteal.instance.config.getInt("Starting-Hearts"))
    }

    fun getHearts(player : Player):Int {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) == null) {
            return 0
        }
        return LifeSteal.instance.dataConfig.getInt("Lifesteal." + player.uniqueId)
    }

    fun addHearts(player : Player, amount : Int) {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) != null) {
            var h : Int = getHearts(player) + amount
            if (h > maxHearts) {
                h = maxHearts
            }
            LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, h)
            player.maxHealth = (h * 2).toDouble()
        }
    }

    fun removeHearts(player : Player, amount : Int) {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) != null) {
            LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, getHearts(player) - amount)
            player.maxHealth = (getHearts(player) - amount * 2).toDouble()
        }
    }

    fun setHearts(player : Player, amount : Int) {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) != null) {
            var h : Int = amount
            if (h > maxHearts) {
                h = maxHearts
            }
            LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, h)
            player.maxHealth = (h * 2).toDouble()
        }
    }

    fun eliminatePlayer(player : Player) {
        if (LifeSteal.instance.dataConfig.get("Lifesteal." + player.uniqueId) != null) {
            LifeSteal.instance.dataConfig.set("Lifesteal." + player.uniqueId, LifeSteal.instance.config.getInt("Starting-Hearts"))
            val cmds : List<String> = LifeSteal.instance.config.getStringList("Eliminate-Commands")
            cmds.forEach{ Bukkit.dispatchCommand(Bukkit.getConsoleSender(), it.replace("%player%", player.name)) }
            LifeSteal.instance.dataConfig.set("Eliminated." + player.name, true)
        }
    }

    fun purgePlayers() {
        if (LifeSteal.instance.dataConfig.get("Eliminated") != null) {
            var players : List<String> = ArrayList<String>(
                LifeSteal.instance.dataConfig.getConfigurationSection("Eliminated")?.getKeys(false))
            val cmd : String? = LifeSteal.instance.config.getString("Purge-Commands")
            players.forEach { Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd!!.replace("%player%", it)) }
            players.forEach { LifeSteal.instance.dataConfig.set("Eliminated.$it", null) }
        }
    }

    private var maxHearts : Int = LifeSteal.instance.config.getInt("Max-Hearts")

}