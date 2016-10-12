package net.s56.homes.mlspropertylistcleaner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.util.List;

/**
 * Created by jaboswell on 9/18/16.
 */
public interface LineParser {
    List<String> parseHeader(String headerString);
    List<String> parseLine(String lineString);
    boolean isHeaderParsed();
    List<String> parseLine(CSVRecord record);
    List<String> getHeaders();
    CSVFormat getFomat();
}
