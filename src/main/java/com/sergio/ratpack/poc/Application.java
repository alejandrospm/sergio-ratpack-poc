package com.sergio.ratpack.poc;

import com.sergio.ratpack.poc.auth.AuthenticationHandler;
import com.sergio.ratpack.poc.handlers.PingHandler;
import com.sergio.ratpack.poc.handlers.visa.VisaActions;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

/**
 * Created by Sergio on 08/01/2017.
 */
public class Application {

	private static final String PING = "ping";
	private static final String VISA = "visa";

	public static void main(String... args) throws Exception {
		RxRatpack.initialize();
		RatpackServer.start(server -> server.registry(
			Guice.registry(b -> b.module(Config.class)))
			.handlers(chain -> chain
				.all(AuthenticationHandler.class)
				.path(PingHandler.class)
				.path(PING, PingHandler.class)
				.prefix(VISA, VisaActions.class))
		);
	}
}
