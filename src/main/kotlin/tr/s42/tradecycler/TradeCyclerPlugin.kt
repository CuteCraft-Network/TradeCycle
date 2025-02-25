package tr.s42.tradecycler

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.route.Route
import org.bukkit.event.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import tr.s42.tradecycler.common.UpdateCheckTask
import tr.s42.tradecycler.listener.VillagerCycleListener
import tr.s42.tradecycler.service.ConfigService
import tr.s42.tradecycler.service.TradeCycleService
import tr.s42.tradecycler.service.TradeCycleService.Companion.strategies

class TradeCyclerPlugin : JavaPlugin() {

    private lateinit var plugin: TradeCyclerPlugin

    override fun onLoad() {
        plugin = this
    }

    override fun onEnable() {
        checkForUpdates()

        val config: YamlDocument = ConfigService(dataFolder, classLoader).createConfig("config")
        val service = TradeCycleService(config)
        registerStrategies(service, config)
    }

    @Suppress("UnstableApiUsage")
    private fun checkForUpdates() {
        UpdateCheckTask { version ->
            if (pluginMeta.version != version) {
                logger.warning("A new version of TradeCycler is available: $version")
                return@UpdateCheckTask
            }

            logger.info("TradeCycler is up to date.")
        }.runTaskAsynchronously(this)
    }

    private fun registerStrategies(service: TradeCycleService, config: YamlDocument) {
        val strategies: List<Listener> = config.getStringList(Route.from("strategy")).map {
            strategies[it] ?: throw IllegalArgumentException("Invalid strategy: $it")
        }

        val pluginManager: PluginManager = server.pluginManager
        pluginManager.registerEvents(VillagerCycleListener(service), plugin)
        strategies.forEach { pluginManager.registerEvents(it, plugin) }
    }

}