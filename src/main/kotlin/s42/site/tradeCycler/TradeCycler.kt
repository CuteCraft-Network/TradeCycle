package s42.site.tradeCycler

import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.MerchantInventory
import org.bukkit.plugin.java.JavaPlugin

class TradeCycler : JavaPlugin(), Listener {

    private var cycleKey: String = "R"

    override fun onEnable() {
        saveDefaultConfig()
        loadConfiguration()
        server.pluginManager.registerEvents(this, this)
    }

    private fun loadConfiguration() {
        val configuredKey = config.getString("button.type")
        if (configuredKey.isNullOrEmpty()) {
            logger.warning("No key configured in config.yml! Using 'R' as default.")
            cycleKey = "R"
        } else {
            cycleKey = configuredKey.uppercase()
        }
    }

    @EventHandler
    fun onKeyPress(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        val inventory = player.openInventory.topInventory

        if (inventory.type != InventoryType.MERCHANT) return
        event.isCancelled = true

        val merchantInv = inventory as MerchantInventory
        val trader = merchantInv.trader
        if (trader !is Villager) return

        when {
            !trader.hasTraded() -> {
                trader.recipes.shuffle()
                player.sendMessage("§aTrades cycled!")
                player.closeInventory()
                player.openMerchant(trader, true)
            }
            else -> player.sendMessage("§cCannot cycle trades - villager has been traded with!")
        }
    }
}