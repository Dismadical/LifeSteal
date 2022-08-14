package rip.stealth.lifesteal.utils

import net.md_5.bungee.api.ChatColor
import java.util.regex.Matcher
import java.util.regex.Pattern

object CC {

    private val pattern = Pattern.compile("&#[a-fA-F0-9]{6}")

    fun translate(`in`: String): String {
        var string = `in`
        var match: Matcher = pattern.matcher(string)
        while (match.find()) {
            val color = string.substring(match.start() + 1, match.end())
            string = string.replace("&$color", ChatColor.of(color).toString() + "")
            match = pattern.matcher(string)
        }

        return ChatColor.translateAlternateColorCodes('&', string)
    }

    fun translate(lines: List<String>): List<String> {
        val toReturn = ArrayList<String>()
        for (line in lines) {
            toReturn.add(translate(line))
        }

        return toReturn
    }

    fun translate(lines: Array<String>): Array<String> {
        val toReturn = ArrayList<String>()
        for (line in lines) {
            toReturn.add(translate(line))
        }

        return toReturn.toTypedArray()
    }

}