package net.cutecraft.core.common

import org.bukkit.entity.Player

interface IMessageService {

    fun sendActionBar(player: Player, message: String)

}