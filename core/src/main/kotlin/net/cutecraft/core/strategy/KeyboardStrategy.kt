package net.cutecraft.core.strategy

import net.cutecraft.core.event.VillagerCycleTradeEvent
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.MerchantInventory
import org.bukkit.Bukkit

class KeyboardStrategy : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        if (event.isCancelled) {
            return
        }

        if (event.click != ClickType.SWAP_OFFHAND) {
            return
        }

        val player = event.whoClicked as Player
        val topInventory: Inventory = player.openInventory.topInventory
        if (topInventory !is MerchantInventory) {
            return
        }

        val holder = topInventory.holder
        if (holder !is Villager) {
            return
        }

        event.isCancelled = true
        Bukkit.getPluginManager().callEvent(VillagerCycleTradeEvent(player, holder))
    }

}