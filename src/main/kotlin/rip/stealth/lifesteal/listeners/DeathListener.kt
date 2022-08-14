package rip.stealth.lifesteal.listeners

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.DeathHandler
import rip.stealth.lifesteal.utils.Language
import rip.stealth.lifesteal.utils.LifeStealUtils

class DeathListener : Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {

        val player: Player = event.entity
        if (DeathHandler.getIfAllDeathsLoseHearts()) {
            LifeStealUtils.removeHearts(player, DeathHandler.getHeartsLostPerDeath())
            if (LifeStealUtils.getHearts(player) == 0) {
                LifeStealUtils.eliminatePlayer(player)
            }

            if (Language.DEATH_MESSAGES) {
                if (Language.ANNOUNCE_DEATHS_ENABLED) {
                    Bukkit.broadcastMessage(CC.translate(Language.ANNOUNCE_DEATHS_MESSAGE?.replace("%player%", player.name)?.replace("%hearts%", LifeStealUtils.getHearts(player).toString())!!))
                }
            }
        }

    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {

        val victim = event.entity
        var damager = event.damager

        if (event.isCancelled) return
        if (victim !is Player) return
        if (victim.health - event.finalDamage > 0) return

        if (DeathHandler.getIfAllDeathsLoseHearts()) {
            if (damager is Player || damager is Projectile) {
                if (damager is Player) damager = damager
                if (damager is Projectile && damager.shooter is Player) damager = damager.shooter as Player
                LifeStealUtils.addHearts(damager as Player, DeathHandler.getHeartsGainedPerKill())
                if (DeathHandler.getIfRegainDeathsAfterKill()) {
                    damager.health = damager.maxHealth
                }

                if (Language.DEATH_MESSAGES) {
                    if (Language.KILL_MESSAGES_TO_PLAYER_ENABLED) {
                        damager.sendMessage(CC.translate(Language.KILL_MESSAGES_TO_PLAYER_MESSAGE?.replace("%player%", victim.name)?.replace("%hearts%", LifeStealUtils.getHearts(damager).toString())!!))
                    }
                    if (Language.DEATH_MESSAGES_TO_PLAYER_ENABLED) {
                        victim.sendMessage(CC.translate(Language.DEATH_MESSAGES_TO_PLAYER_MESSAGE?.replace("%hearts%", LifeStealUtils.getHearts(victim).toString())!!))
                    }
                }
            }
            return
        }

        if (damager is Player) {
            if (!DeathHandler.getIfPlayerCausedDeaths()) return
            LifeStealUtils.addHearts(damager, DeathHandler.getHeartsGainedPerKill())
            LifeStealUtils.removeHearts(victim, DeathHandler.getHeartsLostPerDeath())
            if (LifeStealUtils.getHearts(victim) == 0) {
                LifeStealUtils.eliminatePlayer(victim)
            }
            if (DeathHandler.getIfRegainDeathsAfterKill()) {
                damager.health = damager.maxHealth
            }

            if (Language.DEATH_MESSAGES) {
                if (Language.KILL_MESSAGES_TO_PLAYER_ENABLED) {
                    damager.sendMessage(CC.translate(Language.KILL_MESSAGES_TO_PLAYER_MESSAGE?.replace("%player%", victim.name)?.replace("%hearts%", LifeStealUtils.getHearts(damager).toString())!!))
                }
                if (Language.DEATH_MESSAGES_TO_PLAYER_ENABLED) {
                    victim.sendMessage(CC.translate(Language.DEATH_MESSAGES_TO_PLAYER_MESSAGE?.replace("%hearts%", LifeStealUtils.getHearts(victim).toString())!!))
                }
            }

            return
        }

        if (damager is Projectile) {
            if (!DeathHandler.getIfProjectileCausedDeaths()) return
            damager = damager.shooter as Player
            LifeStealUtils.addHearts(damager, DeathHandler.getHeartsGainedPerKill())
            LifeStealUtils.removeHearts(victim, DeathHandler.getHeartsLostPerDeath())
            if (LifeStealUtils.getHearts(victim) == 0) {
                LifeStealUtils.eliminatePlayer(victim)
            }
            if (DeathHandler.getIfRegainDeathsAfterKill()) {
                damager.health = damager.maxHealth
            }

            if (Language.DEATH_MESSAGES) {
                if (Language.KILL_MESSAGES_TO_PLAYER_ENABLED) {
                    damager.sendMessage(CC.translate(Language.KILL_MESSAGES_TO_PLAYER_MESSAGE?.replace("%player%", victim.name)?.replace("%hearts%", LifeStealUtils.getHearts(damager).toString())!!))
                }
                if (Language.DEATH_MESSAGES_TO_PLAYER_ENABLED) {
                    victim.sendMessage(CC.translate(Language.DEATH_MESSAGES_TO_PLAYER_MESSAGE?.replace("%hearts%", LifeStealUtils.getHearts(victim).toString())!!))
                }
            }
        }



        return
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        val player = event.player
        player.maxHealth = (LifeStealUtils.getHearts(player) * 2).toDouble()
        player.health = player.maxHealth
    }

}