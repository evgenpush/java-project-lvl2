package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;


public class Differ {
    public static final String UNCHANGED = "unchanged";
    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String UPDATED = "updated";
    public static final String OLD_VALUE = "oldValue";
    public static final String NEW_VALUE = "newValue";
    public static final String STATUS = "status";
    public static final String KEY = "key";


    public  static String generate(String file1, String file2) throws Exception {
        return Differ.generate(file1, file2, Formatter.DEFAULT_FORMAT);
    }

    public static String generate(String file1, String file2, String typeFormat) throws Exception {

        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);

        List<Map<String, Object>> difData = Tree.build(map1, map2);

        return Formatter.format(difData, typeFormat);
    }

    public static Map getData(String file) throws Exception {
        int lastDotPosition = file.lastIndexOf(".");
        Path path = Paths.get(file);
        String dataFormat = path.toString().substring(lastDotPosition + 1);
        String content = Files.readString(path);
        return Parser.parse(content, dataFormat);
    }
}
