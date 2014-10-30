package se.chas.projecttemplate.data.models;

import java.io.Serializable;

public class CountrylanguagePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4824723012468929355L;

	private String countryCode;
	private String language;

	public CountrylanguagePK() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLanguage() {
		return this.language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CountrylanguagePK)) {
			return false;
		}
		CountrylanguagePK castOther = (CountrylanguagePK)other;
		return 
			this.countryCode.equals(castOther.countryCode)
			&& this.language.equals(castOther.language);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.countryCode.hashCode();
		hash = hash * prime + this.language.hashCode();
		
		return hash;
	}
}
