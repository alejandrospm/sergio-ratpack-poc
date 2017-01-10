package com.sergio.ratpack.poc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by spedraza on 12/23/2016.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisaRequestDTO implements Serializable {

	@JsonProperty("CardId")
	private String cardId;

	@JsonProperty("ExternalUserId")
	private String externalUserId;

	@JsonProperty("MessageId")
	private String messageId;

	@JsonProperty("MessageName")
	private String messageName;

	@JsonProperty("UserProfileId")
	private String userProfileId;

	@JsonProperty("MessageElementsCollection")
	private List<VisaFieldDTO> messageElementsCollection;

	@JsonProperty("UserDefinedFieldsCollection")
	private List<VisaFieldDTO> userDefinedFieldsCollection;

	public String getMessageElementValue(VisaFieldKeys keyValue){
		return getElementValueFromList(this.getMessageElementsCollection(), keyValue.getKeyName());
	}

	public String getUserDefinedFieldValue(VisaFieldKeys keyValue){
		return getElementValueFromList(this.userDefinedFieldsCollection, keyValue.getKeyName());
	}

	private String getElementValueFromList(List<VisaFieldDTO> elements, String keyValue){
		return elements.stream()
				.filter(e -> e.getKey().equals(keyValue))
				.findFirst()
				.get().getValue();
	}
}
