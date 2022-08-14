package rip.stealth.lifesteal

import com.jonahseguin.drink.CommandService
import com.jonahseguin.drink.Drink
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.java.JavaPlugin
import rip.stealth.lifesteal.commands.essential.*
import rip.stealth.lifesteal.commands.lifesteal.LifestealCommand
import rip.stealth.lifesteal.commands.lifesteal.sub.*
import rip.stealth.lifesteal.listeners.CraftableHeartListener
import rip.stealth.lifesteal.listeners.DeathListener
import rip.stealth.lifesteal.listeners.JoinListener
import rip.stealth.lifesteal.papi.PlaceHolders
import rip.stealth.lifesteal.utils.CC
import rip.stealth.lifesteal.utils.CustomFile

class LifeSteal : JavaPlugin() {

    companion object {
        @JvmStatic
        lateinit var instance: LifeSteal
    }

    var drink : CommandService? = null
    private lateinit var dataFile: CustomFile
    lateinit var dataConfig : FileConfiguration
    lateinit var languageFile: CustomFile
    lateinit var languageConfig : FileConfiguration
    lateinit var craftableHeart: ItemStack
    lateinit var craftableHeartRecipe: ShapedRecipe

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        registerCommands()
        registerListeners()
        registerPAPI()
        registerCustomFiles()
        createCraftableHearts()
    }

    override fun onDisable() {
        saveConfig()
        saveCustomFiles()
    }

    private fun registerCommands() {
        drink = Drink.get(this)
        drink?.register(LifestealCommand, "lifesteal")
            ?.registerSub(LifestealReloadCommand)
            ?.registerSub(LifestealSetCommand)
            ?.registerSub(LifestealGiveCommand)
            ?.registerSub(LifestealRemoveCommand)
            ?.registerSub(LifestealCheckCommand)
            ?.registerSub(LifestealGiveallCommand)
            ?.registerSub(LifestealEliminateCommand)
            ?.registerSub(LifestealPurgeCommand)
            ?.registerSub(LifestealCraftableHeartCommand)
        drink?.register(GameModeCommand, "gamemode", "gm")
        drink?.register(HealCommand, "heal")
        drink?.register(FeedCommand, "feed")
        drink?.register(ClearCommand, "clear", "ci")
        drink?.register(EnchantCommand, "enchant")
        drink?.register(RenameCommand, "rename")
        drink?.register(TpCommand, "tp", "teleport")
        drink?.register(TpHereCommand, "tphere", "teleporhere", "s")
        drink?.register(TpPosCommand, "tppos", "teleportpos", "teleportposition")
        drink?.register(GmcCommand, "gmc")
        drink?.register(GmsCommand, "gms")
        drink?.registerCommands()
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(JoinListener(), this)
        server.pluginManager.registerEvents(DeathListener(), this)
        server.pluginManager.registerEvents(CraftableHeartListener(), this)
    }

    private fun registerPAPI() {
        if (server.pluginManager.getPlugin("PlaceholderAPI") != null) {
            PlaceHolders().register()
        }
    }

    private fun registerCustomFiles() {

        dataFile = CustomFile("data")
        dataConfig = dataFile.config

        languageFile = CustomFile("language")
        languageConfig = languageFile.config

    }

    private fun saveCustomFiles() {
        dataConfig.save(dataFile.file!!)
        languageConfig.save(languageFile.file!!)
    }

    private fun createCraftableHearts() {

        if (!config.getBoolean("Craftable-Hearts.Enabled")) {
            return
        }

        craftableHeart = ItemStack(Material.valueOf(config.getString("Craftable-Hearts.Given-Item.Material")!!), config.getInt("Craftable-Hearts.Given-Item.Amount")!!)
        val meta: ItemMeta = craftableHeart.itemMeta!!
        meta.setDisplayName(CC.translate(config.getString("Craftable-Hearts.Given-Item.Name")!!))
        meta.lore = CC.translate(config.getStringList("Craftable-Hearts.Given-Item.Lore"))
        craftableHeart.itemMeta = meta

        craftableHeartRecipe = ShapedRecipe(NamespacedKey(this, "craftable-heart"), craftableHeart)
        val lines: ArrayList<String> = ArrayList(config.getStringList("Craftable-Hearts.Recipe.Recipe"))
        craftableHeartRecipe.shape(lines[0], lines[1], lines[2])
        val ingredients: ArrayList<String> = ArrayList(config.getConfigurationSection("Craftable-Hearts.Recipe.Ingredients")?.getKeys(false)!!)
        ingredients.forEach {
            craftableHeartRecipe.setIngredient(it[0], Material.valueOf(config.getString("Craftable-Hearts.Recipe.Ingredients.$it.Material")!!))
        }
        Bukkit.addRecipe(craftableHeartRecipe)
    }

}