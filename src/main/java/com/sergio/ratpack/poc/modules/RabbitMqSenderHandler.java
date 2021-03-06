package com.sergio.ratpack.poc.modules;

import com.buzzpoints.rqt.common.model.paymentmethod.dto.CreditCardNormalizedIncomingRequestDTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import ratpack.func.Action;

import java.io.IOException;

/**
 * Created by spedraza on 1/10/2017.
 */
@Slf4j
public class RabbitMqSenderHandler implements Action<CreditCardNormalizedIncomingRequestDTO>{

	public static void sendMessageToQueue(String visaRequestDTO) throws IOException {

		log.info("Starting to send message to queue");
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.basicPublish("visa.exchange.test", "visa.routing.test", MessageProperties.PERSISTENT_TEXT_PLAIN, visaRequestDTO.toString().getBytes());
		log.info("!!!! Message put in the queue !!!!");
		channel.close();
		connection.close();
	}

	@Override
	public void execute(CreditCardNormalizedIncomingRequestDTO creditCardNormalizedIncomingRequestDTO) throws Exception {

	}
}
