package model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private Integer regionCode;
	@Id
	private String number;
	private String numberType;
	
	public Phone() {}
	
	public Integer getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
