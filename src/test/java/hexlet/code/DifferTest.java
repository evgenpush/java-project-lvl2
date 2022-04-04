package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testDiffer() throws Exception {
        var file1 = "file3.json";
        var file2 = "file4.json";
        var file3 = "file3.yaml";
        var file4 = "file4.yaml";

        var format = "stylish";
        var format2 = "plain";

        String result = "{"
            + "\n    chars1: [a, b, c]"
            + "\n  - chars2: [d, e, f]"
            + "\n  + chars2: false"
            + "\n  - checked: false"
            + "\n  + checked: true"
            + "\n  - default: null"
            + "\n  + default: [value1, value2]"
            + "\n  - id: 45"
            + "\n  + id: null"
            + "\n  - key1: value1"
            + "\n  + key2: value2"
            + "\n    numbers1: [1, 2, 3, 4]"
            + "\n  - numbers2: [2, 3, 4, 5]"
            + "\n  + numbers2: [22, 33, 44, 55]"
            + "\n  - numbers3: [3, 4, 5]"
            + "\n  + numbers4: [4, 5, 6]"
            + "\n  + obj1: {nestedKey=value, isNested=true}"
            + "\n  - setting1: Some value"
            + "\n  + setting1: Another value"
            + "\n  - setting2: 200"
            + "\n  + setting2: 300"
            + "\n  - setting3: true"
            + "\n  + setting3: none"
            + "\n}";

        String result2 = "Property 'chars2' was updated. From [complex value] to false"
            + "\nProperty 'checked' was updated. From false to true"
            + "\nProperty 'default' was updated. From null to [complex value]"
            + "\nProperty 'id' was updated. From 45 to null"
            + "\nProperty 'key1' was removed"
            + "\nProperty 'key2' was added with value: 'value2'"
            + "\nProperty 'numbers2' was updated. From [complex value] to [complex value]"
            + "\nProperty 'numbers3' was removed"
            + "\nProperty 'numbers4' was added with value: [complex value]"
            + "\nProperty 'obj1' was added with value: [complex value]"
            + "\nProperty 'setting1' was updated. From 'Some value' to 'Another value'"
            + "\nProperty 'setting2' was updated. From 200 to 300"
            + "\nProperty 'setting3' was updated. From true to 'none'";

        String plainResult = "Property 'follow' was removed\n"
            + "Property 'proxy'\n"
            + "Property 'timeout' was update. From 50 to 20\n"
            + "Property 'verbose' was added with value: 'true'\n";

        String testResult = Differ.generate(file1, file2, format);
        assertEquals(result, testResult);



        String testResult2 = Differ.generate(file3, file4, format);
        assertEquals(result, testResult2);

        String testResult3 = Differ.generate(file1, file4, format);
        assertEquals(result, testResult3);

        String testResult4 = Differ.generate(file3, file4, format2);
        assertEquals(result2, testResult4);

    }
}
