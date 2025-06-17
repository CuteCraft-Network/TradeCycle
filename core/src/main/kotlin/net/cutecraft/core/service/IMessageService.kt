package net.cutecraft.core.service

import org.bukkit.entity.Player

interface IMessageService {

    fun sendActionBar(player: Player, message: String)

}