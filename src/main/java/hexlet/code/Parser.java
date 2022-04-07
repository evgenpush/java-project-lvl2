package hexlet.code;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class Parser {

    public static Map<String, Object> parse(Path path) throws Exception  {
        final String json = "json";
        final String yaml = "yaml";
        final int lenExtension = 4;

        String content;
        String ext = path.toString().substring(path.toString().length() - lenExtension);

        content = Files.readString(path);
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
