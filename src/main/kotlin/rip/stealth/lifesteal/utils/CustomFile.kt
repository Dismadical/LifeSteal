package rip.stealth.lifesteal.utils

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import rip.stealth.lifesteal.LifeSteal
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class CustomFile(val path: String) {

    var file: File? = null
    lateinit var config: FileConfiguration

    init {
        create()
    }

    private fun create() {
        file = File(LifeSteal.instance.dataFolder, "$path.yml")
        if (!file!!.exists()) {
            file!!.parentFile.mkdir()
            LifeSteal.instance.saveResource("$path.yml", false)
        }
        config = YamlConfiguration()
        try {
            config.load(file!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun reload() {
        config = YamlConfiguration.loadConfiguration(file!!)
    }



}