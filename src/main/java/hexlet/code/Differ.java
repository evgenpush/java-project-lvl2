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
    public static final String DEFAULT_FORMAT = "stylish";

    public  static String generate(String file1, String file2) throws Exception {
        return Differ.generate(file1, file2, DEFAULT_FORMAT);
    }

    public static String generate(String file1, String file2, String format) throws Exception {

        Map<String, Object> map1 = getData(file1);
        Map<String, Object> map2 = getData(file2);

        List<Map<String, Object>> difData = Tree.build(map1, map2);

        return Formatter.make(difData, format);
    }

    public static Map getData(String file) throws Exception {
        final int lenExtension = 4;
        Path path = Paths.get(file);

        String content;
        String ext = path.toString().substring(path.toString().length() - lenExtension);

        content = Files.readString(path);
        return Parser.parse(content, ext);
    }


}
