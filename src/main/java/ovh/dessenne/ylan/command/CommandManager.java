package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ovh.dessenne.ylan.util.EmbedHelper;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ovh.dessenne.ylan.BotConfig.PREFIX;


public class CommandManager {
    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandManager() throws Exception {
        try {
            String packageName = "ovh.dessenne.ylan.command";
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource(packageName.replace(".", "/"));

            if (resource == null) {
                System.err.println("Erreur : Dossier introuvable : " + packageName);
                return;
            }

            File[] files = new File(resource.toURI()).listFiles();

            if (files == null) {
                System.err.println("Erreur : Dossier vide");
                return;
            }

            for (File file : files) {
                if (!file.getName().endsWith(".class")) continue;

                String className = packageName + "." + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);

                if (!ICommand.class.isAssignableFrom(clazz) || clazz.isInterface()) continue;

                ICommand command = (ICommand) clazz.getDeclaredConstructor().newInstance();
                registerCommand(command);

                if (command instanceof HelpCommand helpCommand) {
                    helpCommand.setCommandManager(this);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des commandes : " + e.getMessage());
            throw e;
        }

    }

    private void registerCommand(ICommand command) {
        commands.put(command.getName(), command);
    }

    public Collection<ICommand> getAllCommands() {
        return commands.values();
    }

    public void handleCommand(MessageReceivedEvent event) {
        try {
            String message = event.getMessage().getContentRaw(); // ex: "!joke bonjour"

            if (!message.startsWith(PREFIX)) return;

            String messageId = event.getMessage().getId();
            System.out.println("Handling command for message ID: " + messageId + " | Content: " + message);

            String[] parts = message.substring(PREFIX.length()).split("\\s+");
            String commandName = parts[0].toLowerCase();
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            ICommand command = commands.get(commandName);

            if (command == null) {
                event.getMessage().replyEmbeds(EmbedHelper.createErrorEmbed("Commande inconnue",
                        "La commande : `" + PREFIX + commandName + "` n'est pas reconnu comme une commande interne \n " +
                                "**Utilisez `!help` pour voir la liste des commandes disponibles**").build()).queue();
                return;
            }

            command.execute(event, args);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            System.out.println("[" + timestamp + "] Commande reçue : " + commandName + " | Auteur : " + event.getAuthor().getName());
        } catch (Exception e) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            System.err.println("[" + timestamp + "] Erreur lors de l'exécution de la commande : " + e.getMessage());
            event.getMessage().replyEmbeds(EmbedHelper.createErrorEmbed("Erreur d'execution", "Une erreur est survenue lors de l'exécution de la commande.").build()).queue();
        }
    }
}