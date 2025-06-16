package net.cutecraft.tradecycle.event

import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class VillagerCycleTradeEvent(val player: Player, val villager: Villager) : Event() {

    companion object {
        @JvmStatic
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

}