package com.sergio.ratpack.poc.handlers.visa;

import com.buzzpoints.rqt.common.model.paymentmethod.dto.CreditCardNormalizedIncomingRequestDTO;
import com.buzzpoints.rqt.common.model.paymentmethod.dto.PaymentMethodType;
import com.buzzpoints.rqt.common.model.paymentmethod.dto.visa.VisaInputResponseDTO;
import com.buzzpoints.rqt.common.model.paymentmethod.dto.visa.VisaTransactionDTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sergio.ratpack.poc.domain.VisaFieldKeys;
import com.sergio.ratpack.poc.domain.VisaRequestDTO;
import com.sergio.ratpack.poc.modules.RabbitMqSenderHandler;
import lombok.extern.slf4j.Slf4j;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.jackson.Jackson;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Sergio on 08/01/2017.
 */
@Slf4j
public class VisaIncomingHandler implements Handler {

	private static final String MILLISECONDS_PART = ".000Z";

	@Override
	public void handle(Context context) throws Exception {
		log.info("Visa Handler working");
		Promise<CreditCardNormalizedIncomingRequestDTO> promise =
				context.parse(Jackson.fromJson(VisaRequestDTO.class))
						.map(this::buildCreditCardNormalizedIncomingRequestDTOObject);

		Blocking.get(() -> {
			RabbitMqSenderHandler.sendMessageToQueue(promise.toString());
//			Thread.sleep(100);
			context.getResponse().status(200);
			return "Request processed";
		})
		.then(dto -> context.getResponse().send());
	}

	private CreditCardNormalizedIncomingRequestDTO buildCreditCardNormalizedIncomingRequestDTOObject(final VisaRequestDTO visaRequestDTO) {
		return new CreditCardNormalizedIncomingRequestDTO()
				.setCreditCardType(PaymentMethodType.VISA)
				.setMerchantName(visaRequestDTO.getMessageElementValue(VisaFieldKeys.VISA_MERCHANT_NAME))
				.setTransactionRedemptionDate(ZonedDateTime.parse(visaRequestDTO.getMessageElementValue(VisaFieldKeys.TIME_STAMP_YYMMDD)+ MILLISECONDS_PART, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
				.setTransactionAmount(Double.parseDouble(visaRequestDTO.getMessageElementValue(VisaFieldKeys.TRANSACTION_AMOUNT)))
				.setOfferId(visaRequestDTO.getMessageElementValue(VisaFieldKeys.OFFER_ID))
				.setVisaInputResponseDTO(buildVisaInputResponseDTOObject(visaRequestDTO));
	}

	private VisaInputResponseDTO buildVisaInputResponseDTOObject(final VisaRequestDTO visaRequestDTO){
		return new VisaInputResponseDTO()
				.setVisaTransactionDTO(buildVisaTransactionDTOObject(visaRequestDTO))
				.setCardId(visaRequestDTO.getCardId())
				.setExternalUserId(visaRequestDTO.getExternalUserId())
				.setMessageId(visaRequestDTO.getMessageId())
				.setMessageName(visaRequestDTO.getMessageName())
				.setUserProfileId(visaRequestDTO.getUserProfileId());
	}

	private VisaTransactionDTO buildVisaTransactionDTOObject(final VisaRequestDTO visaRequestDTO) {
		return new VisaTransactionDTO()
				.setVisaMerchantId(visaRequestDTO.getMessageElementValue(VisaFieldKeys.VISA_MERCHANT_ID))
				.setVisaStoreId(visaRequestDTO.getMessageElementValue(VisaFieldKeys.VISA_STORE_ID))
				.setVisaStoreName(visaRequestDTO.getMessageElementValue(VisaFieldKeys.VISA_STORE_NAME))
				.setPanLastFour(Long.parseLong(visaRequestDTO.getMessageElementValue(VisaFieldKeys.PAN_LAST_FOUR)));
	}

}
