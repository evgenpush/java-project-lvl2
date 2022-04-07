package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class JsonFormater {

    public static String get(List<Map<String, Object>> difData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Writer writer = new StringWriter();

        for (Map<String, Object> data : difData) {
            mapper.writeValue(writer, data);
        }
//        mapper.writeValue(writer, difData);
        return writer.toString();
    }
}
