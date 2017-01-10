package com.sergio.ratpack.poc;

import com.google.inject.AbstractModule;
import com.sergio.ratpack.poc.auth.AuthenticationHandler;
import com.sergio.ratpack.poc.handlers.PingHandler;
import com.sergio.ratpack.poc.handlers.visa.VisaActions;
import com.sergio.ratpack.poc.handlers.visa.VisaIncomingHandler;

/**
 * Created by spedraza on 1/10/2017.
 */
public class Config extends AbstractModule {

	@Override
	protected void configure() {
		bind(AuthenticationHandler.class);
		bind(VisaActions.class);
		bind(VisaIncomingHandler.class);
		bind(PingHandler.class);
	}
}
