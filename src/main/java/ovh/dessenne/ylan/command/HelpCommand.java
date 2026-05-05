package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ovh.dessenne.ylan.util.EmbedHelper;

import static ovh.dessenne.ylan.BotConfig.PREFIX;

public class HelpCommand implements ICommand {
    private CommandManager commandManager;

    public void setCommandManager(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Affiche la liste des commandes disponibles";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder eb = EmbedHelper.createHelpEmbed();
        for (ICommand command : commandManager.getAllCommands()) {
            eb.addField(PREFIX + command.getName(), command.getDescription(), false);
        }
        event.getMessage().replyEmbeds(eb.build()).queue();
    }
}
