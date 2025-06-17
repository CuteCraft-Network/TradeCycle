package net.cutecraft.core.common

import org.bukkit.scheduler.BukkitRunnable
import java.net.URI
import java.util.*

class VersionCheckTask(
    val comparable: (ComparableVersion) -> Unit
) : BukkitRunnable() {

    companion object {
        const val RESOURCE = "https://api.spigotmc.org/legacy/update.php?resource=122805"
    }

    override fun run() {
        try {
            val url = URI(RESOURCE).toURL()
            url.openStream().use { result ->
                val scanner = Scanner(result)
                comparable(ComparableVersion(scanner.next()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}