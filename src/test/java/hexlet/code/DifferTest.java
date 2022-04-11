package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testDiffer() throws Exception {
        var file1 = "src/test/resources/file3.json";
        var file2 = "src/test/resources/file4.json";
        var file3 = "src/test/resources/file3.yaml";
        var file4 = "src/test/resources/file4.yaml";

        var format1 = "stylish";
        var format2 = "plain";
        var format3 = "json";

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

        String jsonResult = "{\"newValue\":[\"a\",\"b\",\"c\"],\"oldValue\":[\"a\",\"b\",\"c\"],\"key\":\"chars1\","
                + "\"status\":\"unchanged\"}{\"newValue\":false,\"oldValue\":[\"d\",\"e\",\"f\"],\"key\":\"chars2\",\""
                + "status\":\"updated\"}{\"newValue\":true,\"oldValue\":false,\"key\":\"checked\",\"status\":\""
                + "updated\"}{\"newValue\":[\"value1\",\"value2\"],\"oldValue\":null,\"key\":\"default\",\"status\""
                + ":\"updated\"}{\"newValue\":null,\"oldValue\":45,\"key\":\"id\",\"status\":\"updated\"}{\"newValue\""
                + ":null,\"oldValue\":\"value1\",\"key\":\"key1\",\"status\":\"removed\"}{\"newValue\":\"value2\",\""
                + "oldValue\":null,\"key\":\"key2\",\"status\":\"added\"}{\"newValue\":[1,2,3,4],\"oldValue\":[1,2,3,4]"
                + ",\"key\":\"numbers1\",\"status\":\"unchanged\"}{\"newValue\":[22,33,44,55],\"oldValue\":[2,3,4,5],"
                + "\"key\":\"numbers2\",\"status\":\"updated\"}{\"newValue\":null,\"oldValue\":[3,4,5],\"key\":\""
                + "numbers3\",\"status\":\"removed\"}{\"newValue\":[4,5,6],\"oldValue\":null,\"key\":\"numbers4\","
                + "\"status\":\"added\"}{\"newValue\":{\"nestedKey\":\"value\",\"isNested\":true},\"oldValue\":null,"
                + "\"key\":\"obj1\",\"status\":\"added\"}{\"newValue\":\"Another value\",\"oldValue\":\"Some value\""
                + ",\"key\":\"setting1\",\"status\":\"updated\"}{\"newValue\":300,\"oldValue\":200,\"key\":\"setting2"
                + "\",\"status\":\"updated\"}{\"newValue\":\"none\",\"oldValue\":true,\"key\":\"setting3\",\"status\""
                + ":\"updated\"}";

        String testResult = Differ.generate(file3, file4, format1);
        assertEquals(result, testResult);

        String testResult2 = Differ.generate(file3, file4, format2);
        assertEquals(result2, testResult2);

        String testResult3 = Differ.generate(file3, file4, format3);
        assertEquals(jsonResult, testResult3);

        String testResult4 = Differ.generate(file1, file2, format1);
        assertEquals(result, testResult4);

        String testResult5 = Differ.generate(file3, file4, format2);
        assertEquals(result2, testResult5);

        String testResult6 = Differ.generate(file3, file4, format3);
        assertEquals(jsonResult, testResult6);



    }
}
