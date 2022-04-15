package hexlet.code;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class Parser {
    private static final String json = "json";
    private static final String yaml = ".yml";

    public static Map<String, Object> parse(String content, String ext) throws Exception  {

        if (ext.equals(json)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        } else if (ext.equals(yaml)) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        }
        return null;
    }
}
