package ovh.dessenne.ylan;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ovh.dessenne.ylan.listener.CommandListener;

import static ovh.dessenne.ylan.BotConfig.TOKEN;

public class Main {
    static void main() throws Exception {
        BotConfig.load();
        JDA api = JDABuilder.createDefault(TOKEN).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        api.addEventListener(new CommandListener());
        api.awaitShutdown();
    }
}
