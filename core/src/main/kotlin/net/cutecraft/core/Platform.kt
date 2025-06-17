package net.cutecraft.core

import net.cutecraft.core.common.ComparableVersion
import net.cutecraft.core.common.VersionCheckTask
import net.cutecraft.core.listener.VillagerCycleListener
import net.cutecraft.core.service.TradeCycleService
import net.cutecraft.core.service.TradeCycleService.Companion.strategies
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.util.logging.Logger

class Platform(private val provider: PlatformProvider) {

    private val plugin: Plugin = provider.plugin
    private val logger: Logger = plugin.logger

    fun onEnable() {
        checkForUpdates()

        plugin.saveDefaultConfig()
        val config: FileConfiguration = plugin.config

        val service = TradeCycleService(provider.messageService, config)
        registerStrategies(service, config)
    }

    private fun checkForUpdates() {
        VersionCheckTask { latestVersion ->
            val currentVersion = ComparableVersion(plugin.description.version)
            if (currentVersion < latestVersion) {
                logger.warning("A new version of TradeCycle is available: ${latestVersion.rawVersion}. You are using version: ${currentVersion.rawVersion}")
                logger.warning("Download the latest version at: ${VersionCheckTask.RESOURCE}")
                return@VersionCheckTask
            }
        }.runTaskAsynchronously(plugin)
    }

    private fun registerStrategies(service: TradeCycleService, config: FileConfiguration) {
        val strategies: List<Listener> = config.getStringList("strategy").map {
            strategies[it] ?: throw IllegalArgumentException("Invalid strategy: $it")
        }

        val pluginManager: PluginManager = plugin.server.pluginManager
        pluginManager.registerEvents(VillagerCycleListener(service, provider.openInventoryViewFunction, plugin), plugin)
        strategies.forEach { pluginManager.registerEvents(it, plugin) }
    }

}