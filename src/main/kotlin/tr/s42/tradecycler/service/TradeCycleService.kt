package tr.s42.tradecycler.service

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.route.Route
import org.bukkit.entity.Player
import org.bukkit.entity.Villager

class TradeCycleService(private val config: YamlDocument) {

    fun sendMessage(player: Player, messageKey: String) {
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