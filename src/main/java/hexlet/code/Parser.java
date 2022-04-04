package hexlet.code;
import java.util.Map;
import java.util.Collection;
import java.util.AbstractMap;

class Parser {
    private static String defaultFormat = "stylish";
    private static String plainFormat = "plain";
    private static String add = " +";
    private static String remove = " -";
    private static String updateFrom = "1-";
    private static String updateTo = "2+";

    public static String parse(Map<String, Object> difMap, String format) throws Exception  {

        StringBuilder difJson = new StringBuilder();
        if (format.equals(defaultFormat)) {

            difJson.append("{\n");

            for (Map.Entry<String, Object> entry : difMap.entrySet()) {
                String key = entry.getKey();
                difJson.append("  ");
                difJson.append(key.substring(key.length() - 1));
                difJson.append(" ");
                difJson.append(key.substring(0, key.length() - 2));
                difJson.append(": ");
                if (entry.getValue() != null) {
                    difJson.append(entry.getValue().toString());
                } else {
                    difJson.append("null");
                }
                difJson.append("\n");
            }
            difJson.append("}");
        } else if (format.equals(plainFormat)) {

            for (Map.Entry<String, Object> entry : difMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof String) {
                        value = "'" + value.toString() + "'";
                    } else if (value instanceof Collection || value instanceof AbstractMap) {
                        value = "[complex value]";
                    } else {
                        value = value.toString();
                    }

                }

                String flag = key.substring(key.length() - 2);

                if (flag.equals(remove)) {
                    difJson.append("Property '");
                    difJson.append(key.substring(0, key.length() - 2));
                    difJson.append("' was removed");
                    difJson.append("\n");
                } else if (flag.equals(add)) {
                    difJson.append("Property '");
                    difJson.append(key.substring(0, key.length() - 2));
                    difJson.append("' was added with value: ");
                    difJson.append(value);
                    difJson.append("\n");
                } else if (flag.equals(updateFrom)) {
                    difJson.append("Property '");
                    difJson.append(key.substring(0, key.length() - 2));
                    difJson.append("' was updated. From ");
                    difJson.append(value);
                } else if (flag.equals(updateTo)) {
                    difJson.append(" to ");
                    difJson.append(value);
                    difJson.append("\n");
                }
            }
            difJson = difJson.delete(difJson.length() - 1, difJson.length());
        }

        return difJson.toString();
    }
}
