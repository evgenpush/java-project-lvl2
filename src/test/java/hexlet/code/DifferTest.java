package hexlet.code;

import org.junit.jupiter.api.Test;
// import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testDiffer() throws Exception {
        var file1 = "file3.json";
        var file2 = "file4.json";
        var file3 = "file3.yaml";
        var file4 = "file4.yaml";

        var format = "stylish";

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

    }
}
