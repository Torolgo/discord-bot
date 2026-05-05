package ovh.dessenne.ylan.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ovh.dessenne.ylan.util.EmbedHelper;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class JokeCommand implements ICommand {
    @Override
    public String getName() {
        return "joke";
    }

    @Override
    public String getDescription() {
        return "Répond par une blague";
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String[] joke = getJoke();
        EmbedBuilder eb = EmbedHelper.createDefaultEmbed(joke[0], joke[1]);
        event.getMessage().replyEmbeds(eb.build()).queue();
    }

    private String[] getJoke() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new java.net.URI("https://official-joke-api.appspot.com/random_joke"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                org.json.JSONObject jokeObj = new org.json.JSONObject(response.body());
                return new String[]{
                        jokeObj.getString("setup"),
                        jokeObj.getString("punchline")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{"Erreur", "Impossible de récupérer une blague."};
    }
}
