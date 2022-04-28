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
    private static String json;
    private static String plain;
    private static String stylish;
    private final String pathFixtures = "src/test/fixtures/";
    private final String file1 = "file1";
    private final String file2 = "file2";

    @BeforeAll
    public static void init() {
        json = "json";
        plain = "plain";
        stylish = "stylish";
    }

    public String readFile(String file) throws IOException {
        Path path = Paths.get(file);
        return Files.readString(path);
    }

    public String getFixture(String file) throws IOException {
        System.out.println(pathFixtures + file);
        return readFile(pathFixtures + file);
    }

    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    void testDiffer(String ext) throws Exception {
        String expectedResult1 = getFixture("expectedStylish.txt");
        String expectedResult2 = getFixture("expectedPlain.txt");
        String expectedResult3 = getFixture("expectedJson.txt");

        String actualResult1 = Differ.generate(pathFixtures + file1 + ext, pathFixtures + file2 + ext, stylish);
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = Differ.generate(pathFixtures + file1 + ext, pathFixtures + file2 + ext, plain);
        assertEquals(expectedResult2, actualResult2);

        String actualResult3 = Differ.generate(pathFixtures + file1 + ext, pathFixtures + file2 + ext, json);
        assertEquals(expectedResult3, actualResult3);
    }
}
