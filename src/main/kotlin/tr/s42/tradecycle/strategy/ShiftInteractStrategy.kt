package tr.s42.tradecycle.strategy

import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import tr.s42.tradecycle.event.VillagerCycleTradeEvent

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

        VillagerCycleTradeEvent(player, entity).callEvent()
    }

}
