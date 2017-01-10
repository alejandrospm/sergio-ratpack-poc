package com.sergio.ratpack.poc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by spedraza on 12/23/2016.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisaFieldDTO implements Serializable {

	@JsonProperty("Key")
	private String key;

	@JsonProperty("Value")
	private String value;

	public VisaFieldDTO(VisaFieldKeys keyEnum, String value){
		this.key = keyEnum.getKeyName();
		this.value = value;
	}
}
