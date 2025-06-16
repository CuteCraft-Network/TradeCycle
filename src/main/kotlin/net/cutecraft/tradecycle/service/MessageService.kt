package net.cutecraft.tradecycle.service

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object MessageService {

    private val legacyComponentSerializer = LegacyComponentSerializer.legacy('&')

    fun toComponent(message: String): Component {
        return legacyComponentSerializer.deserialize(message)
    }

}