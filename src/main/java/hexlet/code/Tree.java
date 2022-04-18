package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;

public class Tree {


    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> difData = new ArrayList<>();
        Set<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            Map<String, Object> data = new HashMap<>();
            data.put(Differ.KEY, key);
            data.put(Differ.OLD_VALUE, map1.get(key));
            data.put(Differ.NEW_VALUE, map2.get(key));
            String status;

            if (!map1.containsKey(key)) {
                status = Differ.ADDED;
            } else if (!map2.containsKey(key)) {
                status = Differ.REMOVED;
            } else if (equals(map1.get(key), map2.get(key))) {
                status = Differ.UNCHANGED;
            } else {
                status = Differ.UPDATED;
            }
            data.put(Differ.STATUS, status);
            difData.add(data);
        }
        return difData;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
