package com.sergio.ratpack.poc.auth;

import lombok.extern.slf4j.Slf4j;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by Sergio on 08/01/2017.
 */
@Slf4j
public class AuthenticationHandler implements Handler {


    @Override
    public void handle(Context context) throws Exception {
        log.info("Authentication handler working");
        log.info("Doing some very sophisticated things for validating access");
        boolean isValidationOK = true;
        if(isValidationOK) {
            log.info("Validation OK!!!");
            context.next();
        }
        else{
            log.info("ERROR: Validation not passed!!!");
            context.clientError(401);
        }
    }
}
