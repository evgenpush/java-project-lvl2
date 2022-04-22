package hexlet.code;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class Parser {
    private static final String JSON = "json";
    private static final String YML = "yml";
    private static final String YAML = "yaml";

    public static Map<String, Object> parse(String content, String dataFormat) throws Exception  {
        switch (dataFormat) {
            case JSON:
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            case YAML:
            case YML:
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
            default:
                throw new Exception("Error ext");
        }
    }
}
