package net.s56.homes.mlspropertylistcleaner;

import java.util.List;

/**
 * Created by jaboswell on 9/19/16.
 */
public class LineParserConfig {
    private List<String> allFields;
    private List<String> orderedOutputFields;

    public List<String> getAllFields() {
        return allFields;
    }

    public void setAllFields(List<String> allFields) {
        this.allFields = allFields;
    }

    public List<String> getOrderedOutputFields() {
        return orderedOutputFields;
    }

    public void setOrderedOutputFields(List<String> orderedOutputFields) {
        this.orderedOutputFields = orderedOutputFields;
    }
}
