package rip.stealth.lifesteal.papi

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import rip.stealth.lifesteal.utils.LifeStealUtils
import kotlin.math.floor

class PlaceHolders : PlaceholderExpansion() {

    override fun getAuthor(): String {
        return "Stealth Development"
    }

    override fun getVersion(): String {
        return "1.0"
    }

    override fun getIdentifier(): String {
        return "lifesteal"
    }

    override fun canRegister(): Boolean {
        return true
    }

    override fun persist(): Boolean {
        return true
    }

    override fun onPlaceholderRequest(player: Player?, params: String): String? {
        if (player == null) return "Player null"

        when (params) {
            "hearts" -> return LifeStealUtils.getHearts(player).toString()
            "health" -> return floor(player.health / 2).toString()
        }

        return "Invalid Input"
    }

}