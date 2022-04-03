package hexlet.code;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {

        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);
        Map<String, Object> difMap = new TreeMap<>();

        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String key1 = entry.getKey();
            if (map2.containsKey(key1)) {
                if (equals(map2.get(key1), entry.getValue())) {
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

        return Parser.parse(difMap, format);
    }

    public static Map getData(String file) throws Exception {
        final String json = "json";
        final String yaml = "yaml";
        final int lenExtension = 4;


        Path path = Paths.get(file);
        String content = null;
        // System.out.println(path);
        String ext = path.toString().substring(path.toString().length() - lenExtension);

        content = Files.readString(path);
        if (ext.equals(json)) {

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        } else if (ext.equals(yaml)) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        }
        return null;
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
