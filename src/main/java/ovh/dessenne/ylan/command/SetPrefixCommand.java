package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ovh.dessenne.ylan.BotConfig;
import ovh.dessenne.ylan.util.EmbedHelper;

public class SetPrefixCommand implements ICommand {

    @Override
    public String getName() {
        return "setprefix";
    }

    @Override
    public String getDescription() {
        return "Change le préfixe du bot. Usage : " + BotConfig.PREFIX + "setprefix <nouveau_préfixe>";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        if (args.length == 0) {
            event.getMessage().replyEmbeds(
                    EmbedHelper.createWarnEmbed(getName(),
                            "Usage : `" + BotConfig.PREFIX + "setprefix <nouveau_préfixe>`").build()).queue();
            return;
        }

        if (args[0].length() > 3) {
            event.getMessage().replyEmbeds(EmbedHelper.createWarnEmbed(getName(),
                    "Le préfixe ne peut pas dépasser 3 caractères.").build()).queue();
            return;
        }

        String oldPrefix = BotConfig.PREFIX;
        BotConfig.PREFIX = args[0];
        BotConfig.save();

        EmbedBuilder eb = EmbedHelper.createDefaultEmbed("Préfixe mis à jour",
                "Ancien préfixe : `" + oldPrefix + "`\nNouveau préfixe : `" + BotConfig.PREFIX + "`"
        );
        event.getMessage().replyEmbeds(eb.build()).queue();
    }
}