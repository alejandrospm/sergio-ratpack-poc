package com.sergio.ratpack.poc.domain;

/**
 * Created by spedraza on 12/27/2016.
 */
public enum VisaFieldKeys {
	PAN_LAST_FOUR("Transaction.PanLastFour"),
	VISA_MERCHANT_NAME("Transaction.VisaMerchantName"),
	VISA_MERCHANT_ID("Transaction.VisaMerchantId"),
	VISA_STORE_NAME("Transaction.VisaStoreName"),
	VISA_STORE_ID("Transaction.VisaStoreId"),
	TRANSACTION_AMOUNT("Transaction.TransactionAmount"),
	TIME_STAMP_YYMMDD("Transaction.TimeStampYYMMDD"),
	OFFER_ID("Offer.OfferId"),
	MESSAGE_TYPE("MessageType");

	private VisaFieldKeys(String keyName){
		this.keyName = keyName;
	}

	public String getKeyName(){
		return this.keyName;
	}

	private String keyName;
}
