package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormater {

    public static String get(List<Map<String, Object>> difData) {

        StringBuilder difJson = new StringBuilder();

        for (Map<String, Object> data : difData) {
            String oldValue = getValue(data.get(Differ.OLD_VALUE));
            String newValue = getValue(data.get(Differ.NEW_VALUE));
            String key = (String) data.get("key");

            if (data.get(Differ.STATUS).equals(Differ.ADDED)) {
                difJson.append("Property '");
                difJson.append(key);
                difJson.append("' was added with value: ");
                difJson.append(newValue);
                difJson.append("\n");
            } else if (data.get("status").equals(Differ.REMOVED)) {
                difJson.append("Property '");
                difJson.append(key);
                difJson.append("' was removed");
                difJson.append("\n");
            } else if (data.get("status").equals(Differ.UPDATED)) {
                difJson.append("Property '");
                difJson.append(key);
                difJson.append("' was updated. From ");
                difJson.append(oldValue);
                difJson.append(" to ");
                difJson.append(newValue);
                difJson.append("\n");
            }
        }

        difJson = difJson.delete(difJson.length() - 1, difJson.length());
        return difJson.toString();
    }

    public static String getValue(Object value) {
        String v = "null";
        if (value != null) {
            if (value instanceof String) {
                v = "'" + value + "'";
            } else if (value instanceof Collection || value instanceof AbstractMap) {
                v = "[complex value]";
            } else {
                v = value.toString();
            }
        }
        return v;
    }
}
