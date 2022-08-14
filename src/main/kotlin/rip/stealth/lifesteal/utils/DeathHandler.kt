package rip.stealth.lifesteal.utils

import rip.stealth.lifesteal.LifeSteal

object DeathHandler {

    fun getHeartsGainedPerKill(): Int {
        return LifeSteal.instance.config.getInt("Hearts-Gained-Per-Kill")
    }

    fun getHeartsLostPerDeath(): Int {
        return LifeSteal.instance.config.getInt("Hearts-Lost-Per-Death")
    }

    fun getIfAllDeathsLoseHearts(): Boolean {
        return LifeSteal.instance.config.getBoolean("All-Deaths-Lose-Hearts")
    }

    fun getIfPlayerCausedDeaths(): Boolean {
        return LifeSteal.instance.config.getBoolean("Player-Caused-Deaths")
    }

    fun getIfProjectileCausedDeaths(): Boolean {
        return LifeSteal.instance.config.getBoolean("Projectile-Caused-Deaths")
    }

    fun getIfRegainDeathsAfterKill() : Boolean {
        return LifeSteal.instance.config.getBoolean("Regain-Hearts-After-Kill")
    }

}