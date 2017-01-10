package com.sergio.ratpack.poc.handlers.visa;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by Sergio on 09/01/2017.
 */
@Slf4j
public class VisaActions implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        log.info("VisaActions working");
        chain.post("incoming", VisaIncomingHandler.class);
        chain.path("ack", VisaIncomingHandler.class);
    }
}
