package com.sergio.ratpack.poc.handlers.visa;

import com.sergio.ratpack.poc.domain.VisaRequestDTO;
import com.sergio.ratpack.poc.modules.RabbitMQConnection;
import lombok.extern.slf4j.Slf4j;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;
import ratpack.rx.RxRatpack;
import rx.Observable;

/**
 * Created by Sergio on 08/01/2017.
 */
@Slf4j
public class VisaIncomingHandler implements Handler {

	@Override
	public void handle(Context context) throws Exception {
		try {
			Promise<VisaRequestDTO> visaRequestDTOPromise = context.parse(Jackson.fromJson(VisaRequestDTO.class));
			Observable<VisaRequestDTO> observable = RxRatpack.observe(visaRequestDTOPromise);
			RabbitMQConnection rabbitMQConnection = new RabbitMQConnection();
			rabbitMQConnection.sendMessageToQueue();
			log.info("Visa Handler working");
			context.getResponse().status(200);
			context.getResponse().send();
		}
	}
}
