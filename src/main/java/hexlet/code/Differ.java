package hexlet.code;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.HashMap;

public class Differ {
    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String UPDATED = "updated";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";
    public static final String STATUS = "status";

    public static String generate(String file1, String file2, String format) throws Exception {

        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);
        List<Map<String, Object>> difData = new ArrayList<>();
        Set<String> keys = new TreeSet<>();

        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put(OLD_VALUE, map1.get(key));
            data.put(NEW_VALUE, map2.get(key));
            String status;
            if (map1.containsKey(key)) {
                if (map2.containsKey(key)) {
                    if (equals(map1.get(key), map2.get(key))) {
                        status = UNCHANGED;
                    } else {
                        status = UPDATED;
                    }
                } else {
                    status = REMOVED;
                }
            } else {
                status = ADDED;
            }
            data.put(STATUS, status);
            difData.add(data);
        }

        return Formatter.make(difData, format);
    }

    public static Map getData(String file) throws Exception {

        final String dir = "src/main/resources/";
        Path path = Paths.get(dir, file);

        return Parser.parse(path);
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
