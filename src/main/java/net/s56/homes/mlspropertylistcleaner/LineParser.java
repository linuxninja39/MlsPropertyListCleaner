package net.s56.homes.mlspropertylistcleaner;

/**
 * Created by jaboswell on 9/18/16.
 */
public interface LineParser {
    String parseHeader(String headerString);
    String parseLine(String lineString);
}
