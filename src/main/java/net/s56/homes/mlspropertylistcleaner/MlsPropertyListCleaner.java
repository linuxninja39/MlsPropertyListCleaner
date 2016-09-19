package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.Inject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jaboswell on 9/18/16.
 */
public class MlsPropertyListCleaner {
    private LineParser lineParser;

    @Inject
    public MlsPropertyListCleaner(LineParser lineParser) {
        this.lineParser = lineParser;
    }

    public ArrayList<String> cleanFile(File stream) {
        return null;
    }
}
