package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jaboswell on 9/18/16.
 */
public class WasatchFrontLineParser implements LineParser {
    public final CSVFormat format = CSVFormat.INFORMIX_UNLOAD_CSV;
    private LineParserConfigFactory configFactory;
    private LineParserConfig config;
    private boolean headerParsed;
    private Map<String, Integer> positionMap;

    @Inject
    public WasatchFrontLineParser(LineParserConfigFactory configFactory) throws IOException {
        this.configFactory = configFactory;
        config = configFactory.getConfig(this.getClass());
    }

    @Override
    public List<String> parseHeader(String headerString) {
        headerParsed = true;
        positionMap = new HashMap<>();

        Integer counter = 0;
        for (String element : headerString.split(",")) {
            element = element.replace("\"", "");
            positionMap.put(element, counter);
            counter++;
        }

        return config.getOrderedOutputFields();
    }

    @Override
    public List<String> getHeaders() {
        headerParsed = true;
        return config.getOrderedOutputFields();
    }

    @Override
    public CSVFormat getFomat() {
        return format;
    }

    @Override
    public List<String> parseLine(String lineString) {
        if (!headerParsed) {
            throw new RuntimeException("Headed must be parsed first");
        }
        List<String> elements = Arrays.asList(lineString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1));
        List<String> orderedElements = new ArrayList<>();

        for (String element : config.getOrderedOutputFields()) {
            if (!positionMap.containsKey(element)) {
                throw new RuntimeException("header '" + element + "' not found in headers");
            }

            orderedElements.add(elements.get(positionMap.get(element)));
        }

        return orderedElements;
    }

    public boolean isHeaderParsed() {
        return headerParsed;
    }

    @Override
    public List<String> parseLine(CSVRecord record) {
        List<String> orderedElements = new ArrayList<>();
        for (String element : config.getOrderedOutputFields()) {
            if (!record.isSet(element)) {
                throw new RuntimeException("header '" + element + "' not found in headers");
            }

            orderedElements.add(record.get(element));
        }
        return orderedElements;
    }
}
