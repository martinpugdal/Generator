package dk.martinersej.generator.utils;

import org.apache.commons.lang3.text.WordUtils;

public class StringUtils {

    public static String formatEnum(Enum<?> obj) {
        String name = obj.name();
        return WordUtils.capitalizeFully(name.replace("_", " "));
    }

    public static String formatNumber(long number) { //TODO: formatter forkert
        String minus = "";
        if (number < 0) {
            minus = "-";
            number = Math.abs(number);
        }
        int zeros = (int) Math.floor(Math.log10(number));
        int n = (int) (double) (zeros / 3);
        if (n < 1) {
            return minus + number;
        } else {
            return minus + (number / (Math.pow(1000, n))) + " " + "KMBT".split("")[n];
        }
    }
}
