package s42.site.tradeCycler

import org.bukkit.Bukkit
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.plugin.java.JavaPlugin

class TradeCycler : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEntityEvent) {
        val player = event.player
        val entity = event.rightClicked

        if (entity !is Villager) return
        if (!player.isSneaking) return

        if (!villagerHasJob(entity)) {
            player.sendActionBar("§cCannot cycle trades - villager has no job!")
            return
        }
        if (hasBeenTraded(entity)) {
            player.sendActionBar("§cCannot cycle trades - villager has been traded with!")
            return
        }

        event.isCancelled = true

        val profession = entity.profession
        entity.profession = Villager.Profession.NONE
        entity.profession = profession

        player.sendActionBar("§aTrades cycled!")

        Bukkit.getScheduler().runTaskLater(this, Runnable {
            player.openMerchant(entity, true)
        }, 2L)
    }

    private fun hasBeenTraded(villager: Villager): Boolean {
        return villager.recipes.any { it.uses > 0 }
    }

    private fun villagerHasJob(villager: Villager): Boolean {
        return villager.profession != Villager.Profession.NONE
    }
}