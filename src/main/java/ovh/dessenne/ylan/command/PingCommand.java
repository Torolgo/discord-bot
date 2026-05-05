package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ovh.dessenne.ylan.util.EmbedHelper;

public class PingCommand implements ICommand {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Répond avec Pong!";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder eb = EmbedHelper.createDefaultEmbed("Pong Commande", "Pong!");
        event.getMessage().replyEmbeds(eb.build()).queue();
    }
}
