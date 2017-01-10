package com.sergio.ratpack.poc.handlers;

import lombok.extern.slf4j.Slf4j;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by spedraza on 1/10/2017.
 */
@Slf4j
public class PingHandler implements Handler{

	@Override
	public void handle(Context context) throws Exception {
		log.info("Ping handler working");
		context.getResponse().status(200);
		context.getResponse().contentType("application/json");
		context.getResponse().send("{\"ping\": \"Service working\"}");
	}
}
