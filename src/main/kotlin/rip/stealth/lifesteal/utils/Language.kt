package rip.stealth.lifesteal.utils

import rip.stealth.lifesteal.LifeSteal

object Language {

    val DEATH_MESSAGES: Boolean = LifeSteal.instance.languageConfig.getBoolean("Death-Messages")
    val ANNOUNCE_DEATHS_ENABLED: Boolean = LifeSteal.instance.languageConfig.getBoolean("Announce-Deaths.Enabled")
    val ANNOUNCE_DEATHS_MESSAGE: String? = LifeSteal.instance.languageConfig.getString("Announce-Deaths.Message")
    val DEATH_MESSAGES_TO_PLAYER_ENABLED: Boolean = LifeSteal.instance.languageConfig.getBoolean("Death-Messages-To-Player.Enabled")
    val DEATH_MESSAGES_TO_PLAYER_MESSAGE: String? = LifeSteal.instance.languageConfig.getString("Death-Messages-To-Player.Message")
    val KILL_MESSAGES_TO_PLAYER_ENABLED: Boolean = LifeSteal.instance.languageConfig.getBoolean("Kill-Messages-To-Player.Enabled")
    val KILL_MESSAGES_TO_PLAYER_MESSAGE: String? = LifeSteal.instance.languageConfig.getString("Kill-Messages-To-Player.Message")

}