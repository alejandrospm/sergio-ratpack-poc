package com.sergio.ratpack.poc.modules;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Sergio on 08/01/2017.
 */
@Slf4j
public class VisaModule extends AbstractModule {

    @Override
    protected void configure() {

        log.info("Visa module working");
    }
}
