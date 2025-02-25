package tr.s42.tradecycler.common

import org.bukkit.scheduler.BukkitRunnable
import java.net.URI
import java.util.*

class UpdateCheckTask(
    val version: (String) -> Unit
) : BukkitRunnable() {

    companion object {
        private const val RESOURCE = "https://api.spigotmc.org/legacy/update.php?resource=122805"
    }

    override fun run() {
        try {
            val url = URI(RESOURCE).toURL()
            url.openStream().use { result ->
                val scanner = Scanner(result)
                version(scanner.next())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}