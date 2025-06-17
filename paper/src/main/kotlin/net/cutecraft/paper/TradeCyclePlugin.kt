package net.cutecraft.paper

import net.cutecraft.core.Platform
import net.cutecraft.core.PlatformProvider
import net.cutecraft.core.service.IMessageService
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.MenuType
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.BiFunction

class TradeCyclePlugin : JavaPlugin(), PlatformProvider {

    override val plugin: Plugin = this
    override val messageService: IMessageService = MessageService()

    override fun onEnable() {
        Platform(this).onEnable()
    }

    @Suppress("UnstableApiUsage")
    override val openInventoryViewFunction: BiFunction<Villager, Player, InventoryView> =
        BiFunction { villager, player ->
            MenuType.MERCHANT.builder()
                .merchant(villager)
                .build(player)
        }
}