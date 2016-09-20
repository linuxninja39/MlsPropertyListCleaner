package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jaboswell on 9/18/16.
 */
public class MlsPropertyListCleaner {
    private LineParser lineParser;

    @Inject
    public MlsPropertyListCleaner(LineParser lineParser) {
        this.lineParser = lineParser;
    }

    public List<String> cleanFile(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        Reader fileReader = new FileReader(path.toFile());
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fileReader);

        for (CSVRecord record : records) {
            List<String> line;
            if(lineParser.isHeaderParsed()) {
                line = lineParser.parseLine(record);
            } else {
                line = lineParser.getHeaders();
            }
            lines.add(String.join(",", line));
        }

        return lines;
    }
}
