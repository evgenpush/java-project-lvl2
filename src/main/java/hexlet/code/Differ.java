package hexlet.code;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


class Differ {
    public static String generate(String file1, String file2) throws Exception {

        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);
        Map<String, Object> difMap = new TreeMap<>();

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String key1 = entry.getKey();
            if (map2.containsKey(key1)) {
                if (map2.get(key1).equals(entry.getValue())) {
                    difMap.put(key1 + "  ", entry.getValue());
                } else {
                    difMap.put(key1 + "1-", entry.getValue());
                    difMap.put(key1 + "2+", map2.get(key1));
                }
            } else {
                difMap.put(key1 + " -", entry.getValue());
            }
        }

        for (Map.Entry<String, Object> entry : map2.entrySet()) {
            if (!map1.containsKey(entry.getKey())) {
                difMap.put(entry.getKey() + " +", entry.getValue());
            }
        }

        StringBuilder difJson = new StringBuilder();

        difJson.append("{\n");
        for (Map.Entry<String, Object> entry : difMap.entrySet()) {
            String key = entry.getKey();
            difJson.append("  ");
            difJson.append(key.substring(key.length() - 1));
            difJson.append(" ");
            difJson.append(key.substring(0, key.length() - 2));
            difJson.append(": ");
            difJson.append(entry.getValue().toString());
            difJson.append("\n");
        }
        difJson.append("}");

        return difJson.toString();
    }

    public static Map parse(String file) throws Exception  {

        Path path = Paths.get(file);
        String content = null;

        content = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }

    public static Map getData(String content) throws Exception {
        return parse(content);
    }
}
