package ovh.dessenne.ylan;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.*;

public class BotConfig {
    public static final String TOKEN = Dotenv.load().get("TOKEN");
    public static String PREFIX;

    private static final String CONFIG_FILE = "config.json";

    public static void load() {
        try {
            if (Files.exists(Path.of(CONFIG_FILE))) {
                String content = Files.readString(Path.of(CONFIG_FILE));
                JSONObject json = new JSONObject(content);
                PREFIX = json.optString("prefix", "!");
            } else {
                PREFIX = "!";
                save();
            }
        } catch (Exception e) {
            System.err.println("Erreur lecture config : " + e.getMessage());
            PREFIX = "!";
        }
    }

    public static void save() {
        try {
            JSONObject json = new JSONObject();
            json.put("prefix", PREFIX);
            Files.writeString(Path.of(CONFIG_FILE), json.toString(2));
        } catch (Exception e) {
            System.err.println("Erreur sauvegarde config : " + e.getMessage());
        }
    }
}