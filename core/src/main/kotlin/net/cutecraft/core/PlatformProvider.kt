package net.cutecraft.core

import net.cutecraft.core.common.IMessageService
import org.bukkit.plugin.Plugin

interface PlatformProvider {

    val plugin: Plugin
    val messageService: IMessageService

}