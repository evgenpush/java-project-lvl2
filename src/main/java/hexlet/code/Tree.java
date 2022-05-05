package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;

public class Tree {
    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String UPDATED = "updated";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";
    public static final String STATUS = "status";
    public static final String KEY = "key";


    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> difData = new ArrayList<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> data = new HashMap<>();
            data.put(KEY, key);
            data.put(OLD_VALUE, map1.get(key));
            data.put(NEW_VALUE, map2.get(key));
            String status;

            if (!map1.containsKey(key)) {
                status = ADDED;
            } else if (!map2.containsKey(key)) {
                status = REMOVED;
            } else if (equals(map1.get(key), map2.get(key))) {
                status = UNCHANGED;
            } else {
                status = UPDATED;
            }
            data.put(STATUS, status);
            difData.add(data);
        }
        return difData;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
