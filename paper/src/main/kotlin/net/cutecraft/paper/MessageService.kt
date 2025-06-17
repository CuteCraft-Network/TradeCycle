package net.cutecraft.paper

import net.cutecraft.core.service.IMessageService
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player

class MessageService : IMessageService {

    companion object {
        private val legacyComponentSerializer = LegacyComponentSerializer.legacy('&')
    }

    override fun sendActionBar(player: Player, message: String) {
        player.sendActionBar(legacyComponentSerializer.deserialize(message))
    }
}