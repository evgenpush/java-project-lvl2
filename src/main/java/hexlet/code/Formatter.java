package hexlet.code;

import hexlet.code.formatters.JsonFormater;
import hexlet.code.formatters.PlainFormater;
import hexlet.code.formatters.StylishFormater;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String DEFAULT_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";

    public static String format(List<Map<String, Object>> difData, String dataFormat) throws IOException {

        switch (dataFormat) {
            case DEFAULT_FORMAT:
                return StylishFormater.get(difData);
            case PLAIN_FORMAT:
                return PlainFormater.get(difData);
            case JSON_FORMAT:
                return JsonFormater.get(difData);
            default:
                throw  new IOException("error format");
        }
    }
}
