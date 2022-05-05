package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;


public class Differ {

    public  static String generate(String file1, String file2) throws Exception {
        return Differ.generate(file1, file2, Formatter.DEFAULT_FORMAT);
    }

    public static String generate(String file1, String file2, String typeFormat) throws Exception {

        Map<String, Object> data1 = getData(file1);
        Map<String, Object> data2 = getData(file2);

        List<Map<String, Object>> difData = Tree.build(data1, data2);

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
