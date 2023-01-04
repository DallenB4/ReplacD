package glue_gunner4.replacd.config;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.mojang.datafixers.util.Pair;
import glue_gunner4.replacd.Replacd;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReplacdServerConfig {
    public static ReplacdServerConfig CONFIG;
    public static ArrayList<Pair<String, String>> REPLACEMENTS;

    public static void reloadConfig() {
        Replacd.LOGGER.log(Level.INFO, "GAK!");
        CONFIG = new ReplacdServerConfig("Replac'D.json");
    }

    private ReplacdServerConfig(String fileName) {
        Path path = FabricLoader.getInstance().getConfigDir();
        File configFile = path.resolve(fileName).toFile();
        for (int i = 0; i < 3 && !configFile.exists(); i++) {
            try {
                createConfig(configFile);
            } catch (Exception e) {
                Replacd.LOGGER.log(Level.ERROR, "Unable to create config!");
                e.printStackTrace();
            }
        }
        try {
            readData(configFile);
        } catch (FileNotFoundException e) {
            Replacd.LOGGER.log(Level.ERROR, "Config file not found!");
        } catch (Exception e) {
            Replacd.LOGGER.log(Level.ERROR, "Json is configured incorrectly!");
        }
    }

    private void readData(File configFile) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(configFile));

        REPLACEMENTS = new ArrayList<Pair<String, String>>();
        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                break;
            }
            Pair nextPair = new Pair(reader.nextName(), reader.nextString());
            REPLACEMENTS.add(nextPair);
        }
        Replacd.LOGGER.log(Level.INFO, "Loaded " + REPLACEMENTS.size() + " replacements.");
    }

    private static void createConfig(File configFile) throws IOException {
        configFile.createNewFile();

        JsonWriter writer = new JsonWriter(new FileWriter(configFile));
        writer.setIndent("    ");
        writer.beginObject();
        writer.name("#From").value("#To");
        writer.endObject();
        writer.close();
    }
}
