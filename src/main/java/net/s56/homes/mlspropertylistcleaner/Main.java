package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jaboswell on 9/18/16.
 */
public class Main {
    public static String OPTION_F = "f";
    public static String OPTION_O = "o";

    public static void main(String[] args) throws ParseException {
        CommandLine commandLine = getArgs(args);

        String inputFileName = commandLine.getOptionValue(OPTION_F);
        File inputFile = new File(inputFileName);
        if (!inputFile.canRead()) {
            throw new RuntimeException("Can not read from file: " + inputFileName);
        }
        String outputFileName = commandLine.getOptionValue(OPTION_O);
        File outputFile = new File(outputFileName);
        if (!outputFile.canWrite()) {
            throw new RuntimeException("Can not write to file: " + outputFileName);
        }

        Injector injector = Guice.createInjector(new CleanerModule());

        MlsPropertyListCleaner listCleaner = injector.getInstance(MlsPropertyListCleaner.class);

        ArrayList<String> lines = listCleaner.cleanFile(inputFile);


    }

    private static CommandLine getArgs(String[] args) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        return commandLineParser.parse(setupOptions(), args);
    }

    private static Options setupOptions() {
        Options options = new Options();
        options.addOption(OPTION_F, "File to parse");
        options.addOption(OPTION_O, "File to write");
        return options;
    } }
