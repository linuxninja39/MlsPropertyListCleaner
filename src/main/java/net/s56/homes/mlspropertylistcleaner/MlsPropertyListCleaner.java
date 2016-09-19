package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Inject;

import java.io.IOException;
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
        List<String> lines = new ArrayList<String>();
        Stream<String> stream = Files.lines(path);
        stream.forEach(
                l -> {
                    if (lineParser.isHeaderParsed()) {
                        lines.add(String.join(",", lineParser.parseLine(l)));
                    } else {
                        lines.add(String.join(",", lineParser.parseHeader(l)));
                    }
                }
        );

        return lines;
    }
}
