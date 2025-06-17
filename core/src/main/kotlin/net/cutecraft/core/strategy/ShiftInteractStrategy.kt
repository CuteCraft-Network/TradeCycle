package net.cutecraft.core.strategy

import net.cutecraft.core.event.VillagerCycleTradeEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class ShiftInteractStrategy : Listener {

    @EventHandler
    fun onPlayerInteractEntityEvent(event: PlayerInteractEntityEvent) {
        if (event.isCancelled) {
            return
        }

        val entity = event.rightClicked
        if (entity !is Villager) {
            return
        }

        val player = event.player
        if (!player.isSneaking) {
            return
        }

        Bukkit.getPluginManager().callEvent(VillagerCycleTradeEvent(player, entity))
    }

}
