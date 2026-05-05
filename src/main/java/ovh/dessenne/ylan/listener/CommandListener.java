package ovh.dessenne.ylan.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ovh.dessenne.ylan.command.CommandManager;

public class CommandListener extends ListenerAdapter {
    private final CommandManager commandManager = new CommandManager();

    public CommandListener() throws Exception {
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        commandManager.handleCommand(event);
    }
}