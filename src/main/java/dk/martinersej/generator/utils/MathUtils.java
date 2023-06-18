package dk.martinersej.generator.utils;

import org.apache.commons.lang3.text.WordUtils;

public class MathUtils {

    // random number between min and max
    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
