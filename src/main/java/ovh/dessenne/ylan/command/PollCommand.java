package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.messages.MessagePollBuilder;
import net.dv8tion.jda.api.utils.messages.MessagePollData;
import ovh.dessenne.ylan.util.EmbedHelper;

import java.util.concurrent.TimeUnit;

import static ovh.dessenne.ylan.BotConfig.PREFIX;

public class PollCommand implements ICommand {
    @Override
    public String getName() {
        return "poll";
    }

    @Override
    public String getDescription() {
        return "Crée un sondage. Usage : `" + PREFIX + "poll <question> | <option1> | <option2> | ... [| multi]`";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {

        if (args.length == 0) {
            EmbedBuilder eb = EmbedHelper.createWarnEmbed(getName(), "Usage : `" + PREFIX + "poll <question> | <option1> | <option2> | ... [| multi]`");
            event.getMessage().replyEmbeds(eb.build()).queue();
            return;
        }

        String full = String.join(" ", args);
        String[] parts = full.split("\\|");

        if (parts.length < 3) {
            EmbedBuilder eb = EmbedHelper.createWarnEmbed(getName(), "Il faut au minimum une question et 2 options.");
            event.getMessage().replyEmbeds(eb.build()).queue();
            return;
        }

        String question = parts[0].trim();

        MessagePollBuilder builder = MessagePollData.builder(question)
                .setDuration(24, TimeUnit.HOURS);

        boolean isMulti = parts[parts.length - 1].trim().equalsIgnoreCase("multi");
        int partsLength = parts.length;
        if (isMulti) {
            partsLength = partsLength - 1;
        }
        builder.setMultiAnswer(isMulti);

        for (int i = 1; i < partsLength; i++) {
            builder.addAnswer(parts[i].trim());
        }

        event.getChannel().sendMessagePoll(builder.build()).queue();
    }
}
