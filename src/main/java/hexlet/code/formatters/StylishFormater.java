package hexlet.code.formatters;

import hexlet.code.Tree;

import java.util.List;
import java.util.Map;

public class StylishFormater {

    public static String get(List<Map<String, Object>> difData) {
        final String plus = "  + ";
        final String minus = "  - ";

        StringBuilder difJson = new StringBuilder();
        difJson.append("{\n");
        for (Map<String, Object> data : difData) {
            if (data.get(Tree.STATUS).equals(Tree.ADDED)) {
                difJson.append(plus);
                difJson.append(data.get(Tree.KEY));
                difJson.append(": ");
                difJson.append(data.get(Tree.NEW_VALUE));
            } else if (data.get(Tree.STATUS).equals(Tree.REMOVED)) {
                difJson.append(minus);
                difJson.append(data.get(Tree.KEY));
                difJson.append(": ");
                difJson.append(data.get(Tree.OLD_VALUE));
            } else if (data.get(Tree.STATUS).equals(Tree.UPDATED)) {
                difJson.append(minus);
                difJson.append(data.get(Tree.KEY));
                difJson.append(": ");
                difJson.append(data.get(Tree.OLD_VALUE));
                difJson.append("\n");
                difJson.append(plus);
                difJson.append(data.get(Tree.KEY));
                difJson.append(": ");
                difJson.append(data.get(Tree.NEW_VALUE));
            } else {
                difJson.append("    ");
                difJson.append(data.get(Tree.KEY));
                difJson.append(": ");
                difJson.append(data.get(Tree.OLD_VALUE));
            }
            difJson.append("\n");
        }
        difJson.append("}");
        return difJson.toString();
    }
}
