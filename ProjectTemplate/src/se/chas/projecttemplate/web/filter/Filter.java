package se.chas.projecttemplate.web.filter;

import java.io.Serializable;

public class Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2676192927603689873L;

	private String countryCode;
	
	private String country;
	
	private String continent;
	
	private String region;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
