package tr.s42.tradecycler

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.route.Route
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import tr.s42.tradecycler.common.CycleStrategy
import tr.s42.tradecycler.listener.VillagerCycleListener
import tr.s42.tradecycler.service.ConfigService
import tr.s42.tradecycler.service.TradeCycleService

class TradeCyclerPlugin : JavaPlugin() {

    private lateinit var plugin: TradeCyclerPlugin

    override fun onLoad() {
        plugin = this
    }

    override fun onEnable() {
        val config: YamlDocument = ConfigService.createConfig("config", dataFolder)
        val service = TradeCycleService(config)
        val strategies: List<CycleStrategy> =
            config.getStringList(Route.from("strategy")).map { CycleStrategy.valueOf(it) }

        val pluginManager: PluginManager = server.pluginManager
        pluginManager.registerEvents(VillagerCycleListener(service), this)
        strategies.forEach { pluginManager.registerEvents(it.listener, this) }
    }

}