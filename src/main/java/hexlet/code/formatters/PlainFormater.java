package hexlet.code.formatters;

import hexlet.code.Differ;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormater {

    public static String get(List<Map<String, Object>> difData) {

        StringBuilder plainData = new StringBuilder();

        for (Map<String, Object> data : difData) {
            String oldValue = getValue(data.get(Differ.OLD_VALUE));
            String newValue = getValue(data.get(Differ.NEW_VALUE));
            String key = (String) data.get(Differ.KEY);

            if (data.get(Differ.STATUS).equals(Differ.ADDED)) {
                plainData.append("Property '");
                plainData.append(key);
                plainData.append("' was added with value: ");
                plainData.append(newValue);
                plainData.append("\n");
            } else if (data.get("status").equals(Differ.REMOVED)) {
                plainData.append("Property '");
                plainData.append(key);
                plainData.append("' was removed");
                plainData.append("\n");
            } else if (data.get("status").equals(Differ.UPDATED)) {
                plainData.append("Property '");
                plainData.append(key);
                plainData.append("' was updated. From ");
                plainData.append(oldValue);
                plainData.append(" to ");
                plainData.append(newValue);
                plainData.append("\n");
            }
        }

        plainData = plainData.delete(plainData.length() - 1, plainData.length());
        return plainData.toString();
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
