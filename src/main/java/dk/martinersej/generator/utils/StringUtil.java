package dk.martinersej.generator.utils;

import org.apache.commons.lang3.text.WordUtils;

public class StringUtil {

    public static String formatEnum(Enum<?> obj) {
        String name = obj.name();
        return WordUtils.capitalizeFully(name.replace("_", " "));
    }
}
