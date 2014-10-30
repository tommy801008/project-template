package se.chas.projecttemplate.data.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="countryLanguage")
@NamedQuery(name="Countrylanguage.findAll", query="SELECT c FROM Countrylanguage c")
public class Countrylanguage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3558343407928902206L;

//	@EmbeddedId
//	private CountrylanguagePK id;
	
	@Id
	private String language;
	
	private String isOfficial;
	private float percentage;
	
	@ManyToOne
	@JoinColumn(name="CountryCode" ,referencedColumnName="Code")
	private Country country;
	
	public Countrylanguage() {
	}
	

	public String getIsOfficial() {
		return this.isOfficial;
	}

	public void setIsOfficial(String isOfficial) {
		this.isOfficial = isOfficial;
	}


	public float getPercentage() {
		return this.percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
