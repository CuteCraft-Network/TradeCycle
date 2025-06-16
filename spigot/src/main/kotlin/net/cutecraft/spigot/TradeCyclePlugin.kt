package net.cutecraft.spigot

import net.cutecraft.core.Platform
import net.cutecraft.core.PlatformProvider
import net.cutecraft.core.common.IMessageService
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class TradeCyclePlugin : JavaPlugin(), PlatformProvider {

    override val plugin: Plugin = this
    override val messageService: IMessageService = MessageService()

    override fun onEnable() {
        Platform(this).onEnable()
    }
}