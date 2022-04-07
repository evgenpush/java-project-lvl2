package hexlet.code;

import hexlet.code.formatters.JsonFormater;
import hexlet.code.formatters.PlainFormater;
import hexlet.code.formatters.StylishFormater;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    private static String defaultFormat = "stylish";
    private static String plainFormat = "plain";
    private static String jsonFormat = "json";

    public static String make(List<Map<String, Object>> difData, String format) throws IOException {

        if (format.equals(defaultFormat)) {
            return StylishFormater.get(difData);
        } else if (format.equals(plainFormat)) {
            return PlainFormater.get(difData);
        } else if (format.equals(jsonFormat)) {
            return JsonFormater.get(difData);
        }

        return null;
    }
}
