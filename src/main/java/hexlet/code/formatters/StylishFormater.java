package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class StylishFormater {

    public static String get(List<Map<String, Object>> difData) {
        final String plus = "  + ";
        final String minus = "  - ";

        StringBuilder difJson = new StringBuilder();
        difJson.append("{\n");
        for (Map<String, Object> data : difData) {
            if (data.get(Differ.STATUS).equals(Differ.ADDED)) {
                difJson.append(plus);
                difJson.append(data.get(Differ.KEY));
                difJson.append(": ");
                difJson.append(data.get(Differ.NEW_VALUE));
            } else if (data.get(Differ.STATUS).equals(Differ.REMOVED)) {
                difJson.append(minus);
                difJson.append(data.get(Differ.KEY));
                difJson.append(": ");
                difJson.append(data.get(Differ.OLD_VALUE));
            } else if (data.get(Differ.STATUS).equals(Differ.UPDATED)) {
                difJson.append(minus);
                difJson.append(data.get(Differ.KEY));
                difJson.append(": ");
                difJson.append(data.get(Differ.OLD_VALUE));
                difJson.append("\n");
                difJson.append(plus);
                difJson.append(data.get(Differ.KEY));
                difJson.append(": ");
                difJson.append(data.get(Differ.NEW_VALUE));
            } else {
                difJson.append("    ");
                difJson.append(data.get(Differ.KEY));
                difJson.append(": ");
                difJson.append(data.get(Differ.OLD_VALUE));
            }
            difJson.append("\n");
        }
        difJson.append("}");
        return difJson.toString();
    }
}
