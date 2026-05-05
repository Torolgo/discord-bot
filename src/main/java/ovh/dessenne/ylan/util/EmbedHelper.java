package ovh.dessenne.ylan.util;

import net.dv8tion.jda.api.EmbedBuilder;

import static ovh.dessenne.ylan.BotConfig.PREFIX;

public class EmbedHelper {
    public static EmbedBuilder createDefaultEmbed(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description) //
                .setColor(0x3498db);
    }

    public static EmbedBuilder createHelpEmbed() {
        return new EmbedBuilder()
                .setTitle("Commandes disponibles")
                .setDescription("Voici la liste des commandes :") //
                .setColor(0x009900);
    }

    public static EmbedBuilder createErrorEmbed(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(0xFF0000);
    }

    public static EmbedBuilder createWarnEmbed(String commandName, String description) {
        return new EmbedBuilder()
                .setTitle("Argument manquant ou invalide sur la commande : " + commandName)
                .setDescription("La commande : `" + PREFIX + commandName + "` nécessite des arguments valides\n " +
                        "**" + description + "**")
                .setColor(0xFFA500);
    }
}
