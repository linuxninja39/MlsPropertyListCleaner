package net.s56.homes.mlspropertylistcleaner;

import com.google.inject.AbstractModule;

/**
 * Created by jaboswell on 9/18/16.
 */
public class CleanerModule extends AbstractModule {
    protected void configure() {
        bind(LineParser.class).to(WasatchFrontLineParser.class);
    }
}
