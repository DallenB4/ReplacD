package glue_gunner4.replacd;

import com.mojang.datafixers.util.Pair;
import glue_gunner4.replacd.config.ReplacdServerConfig;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Replacd implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Replac'D");

    @Override
    public void onInitialize() {
        ReplacdServerConfig.reloadConfig();
    }


    public static String ReplaceText (String content) {
        ArrayList<Pair<String, String>> REPLACEMENTS = ReplacdServerConfig.REPLACEMENTS;
        for(int i = 0; i < REPLACEMENTS.size(); i++) {
            content = content.replaceAll(REPLACEMENTS.get(i).getFirst(), REPLACEMENTS.get(i).getSecond());
        }
        return content;
    }
}
