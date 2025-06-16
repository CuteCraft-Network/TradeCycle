package net.cutecraft.tradecycle.service

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.route.Route
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.Listener
import net.cutecraft.tradecycle.strategy.KeyboardStrategy
import net.cutecraft.tradecycle.strategy.ShiftInteractStrategy

class TradeCycleService(private val config: YamlDocument) {

    companion object {
        val strategies: Map<String, Listener> = mapOf(
            "KEYBOARD" to KeyboardStrategy(),
            "SHIFT_INTERACT" to ShiftInteractStrategy()
        )
    }

    fun sendActionBar(player: Player, messageKey: String) {
        val message: String = config.getOptionalString(Route.from("messages", messageKey)).orElseThrow()
        player.sendActionBar(MessageService.toComponent(message))
    }

    fun cycleTrade(villager: Villager) {
        val profession = villager.profession
        villager.profession = Villager.Profession.NONE
        villager.profession = profession
    }

    fun isLocked(villager: Villager): Boolean {
        return villager.villagerExperience > 0
    }

    fun hasProfession(villager: Villager): Boolean {
        return villager.profession != Villager.Profession.NONE
    }

}