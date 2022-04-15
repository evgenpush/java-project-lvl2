package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    @BeforeAll
    public static void init() {
        resultJson = "json";
        resultPlain = "plain";
        resultStylish = "stylish";
    }

    public String getFile(String file) {
        Path path = Paths.get(file);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testDiffer(String ext) throws Exception {
        String path1 = "src/test/resources/file3";
        String path2 = "src/test/resources/file4";

        String result1 = getFile("src/test/resources/resultStylish.txt");
        String result2 = getFile("src/test/resources/resultPlain.txt");
        String result3 = getFile("src/test/resources/resultJson.txt");


        String testResult1 = Differ.generate(path1 + ext, path2 + ext, resultStylish);
        assertEquals(result1, testResult1);

        String testResult2 = Differ.generate(path1 + ext, path2 + ext, resultPlain);
        assertEquals(result2, testResult2);

        String testResult3 = Differ.generate(path1 + ext, path2 + ext, resultJson);
        assertEquals(result3, testResult3);

    }


}
