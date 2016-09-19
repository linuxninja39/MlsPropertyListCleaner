package net.s56.homes.mlspropertylistcleaner;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;

/**
 * Created by jaboswell on 9/19/16.
 */
public class LineParserConfigFactory {
    public static final String FILE_NAME_EXTENTION = ".json";

    public LineParserConfig getConfig(Class c) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String fileName = c.getSimpleName() + FILE_NAME_EXTENTION;
        URL configFileUrl = this.getClass().getClassLoader().getResource(fileName);
        String file = configFileUrl.getFile();
        LineParserConfig lineCleanerConfig = mapper
                .readValue(
                        configFileUrl,
                        LineParserConfig.class
                );
        return lineCleanerConfig;
    }
}
