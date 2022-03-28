package hexlet.code;

import org.junit.jupiter.api.Test;
// import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {

    @Test
    void testDiffer() throws Exception {
        var file1 = "file1.json";
        var file2 = "file2.json";

        String result = "{"
            + "\n  - follow: false"
            + "\n    host: hexlet.io"
            + "\n  - proxy: 123.234.53.22"
            + "\n  - timeout: 50"
            + "\n  + timeout: 20"
            + "\n  + verbose: true"
            + "\n}";

        String testResult = Differ.generate(file1, file2);
        assertEquals(result, testResult);

    }
}
