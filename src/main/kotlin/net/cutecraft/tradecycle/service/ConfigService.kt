package net.cutecraft.tradecycle.service

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import java.io.File
import java.io.InputStream

class ConfigService(
    private val path: File,
    private val classLoader: ClassLoader
) {

    fun createConfig(name: String): YamlDocument {
        val resourceStream: InputStream =
            classLoader.getResourceAsStream("$name.yml")
                ?: throw IllegalArgumentException(
                    "Resource '$name.yml' not found in plugin resources."
                )

        return YamlDocument.create(
            File(path, "$name.yml"),
            resourceStream,
            GeneralSettings.builder().setKeyFormat(GeneralSettings.KeyFormat.OBJECT).build(),
            LoaderSettings.builder().setAutoUpdate(true).build(),
            DumperSettings.DEFAULT,
            UpdaterSettings.builder()
                .setVersioning(BasicVersioning("config-version"))
                .setOptionSorting(UpdaterSettings.OptionSorting.SORT_BY_DEFAULTS)
                .build(),
        )
    }

}