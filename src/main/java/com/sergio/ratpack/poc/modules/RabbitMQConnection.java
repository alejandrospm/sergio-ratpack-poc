package com.sergio.ratpack.poc.modules;

import com.google.inject.AbstractModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sergio.ratpack.poc.domain.VisaRequestDTO;

import java.io.IOException;

/**
 * Created by spedraza on 1/10/2017.
 */
public class RabbitMQConnection{

	private ConnectionFactory connectionFactory;

	public void sendMessageToQueue(VisaRequestDTO visaRequestDTO) throws IOException {

		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(15672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.basicPublish("visa.exchange.test", "visa.routing.test", null, visaRequestDTO.toString().getBytes());
		channel.close();
		connection.close();
	}
}
