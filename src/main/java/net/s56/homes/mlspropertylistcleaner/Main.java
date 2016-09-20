package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaboswell on 9/18/16.
 */
public class Main {
    public static String OPTION_F = "f";
    public static String OPTION_O = "o";

    public static void main(String[] args) throws ParseException, IOException {
        CommandLine commandLine = getArgs(args);

        if (!commandLine.hasOption(OPTION_F)) {
            throw new RuntimeException("-f is a required argument");
        }
        if (!commandLine.hasOption(OPTION_O)) {
            throw new RuntimeException("-o is a required argument");
        }

        String inputFileName = commandLine.getOptionValue(OPTION_F);
        Path inputFile = Paths.get(inputFileName);
        if (!Files.isReadable(inputFile)) {
            throw new RuntimeException("Can not read from file: " + inputFileName);
        }
        String outputFileName = commandLine.getOptionValue(OPTION_O);
        Path outputFile = Paths.get(outputFileName);
        Charset u = StandardCharsets.UTF_8;
        Files.write(outputFile, new ArrayList<>(), u);
        if (!Files.isWritable(outputFile)) {
            throw new RuntimeException("Can not write to file: " + outputFile.toFile().getAbsolutePath());
        }

        Injector injector = Guice.createInjector(new CleanerModule());

        MlsPropertyListCleaner listCleaner = injector.getInstance(MlsPropertyListCleaner.class);

        List<String> lines = listCleaner.cleanFile(inputFile);

        Files.write(outputFile, lines, u);
    }

    private static CommandLine getArgs(String[] args) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        return commandLineParser.parse(setupOptions(), args);
    }

    private static Options setupOptions() {
        Options options = new Options();
        options.addOption(OPTION_F, true, "File to parse");
        options.addOption(OPTION_O, true, "File to write");
        return options;
    } }
