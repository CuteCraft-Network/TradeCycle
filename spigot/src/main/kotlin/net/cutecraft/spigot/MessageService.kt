package net.cutecraft.spigot

import net.cutecraft.core.service.IMessageService
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class MessageService : IMessageService {

    override fun sendActionBar(player: Player, message: String) {
        val coloredMessage = ChatColor.translateAlternateColorCodes('&', message)
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(coloredMessage))
    }
}