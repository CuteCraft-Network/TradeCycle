package net.cutecraft.core

import net.cutecraft.core.service.IMessageService
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.InventoryView
import org.bukkit.plugin.Plugin
import java.util.function.BiFunction

interface PlatformProvider {

    val plugin: Plugin
    val messageService: IMessageService
    val openInventoryViewFunction: BiFunction<Villager, Player, InventoryView>

}