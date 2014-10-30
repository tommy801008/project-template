package se.chas.projecttemplate.data.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4942826150689970351L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;
	
//	private int capital;
	private String code2;
	private String continent;
	private float gnp;
	private float GNPOld;
	private String governmentForm;
	private String headOfState;
	private short indepYear;
	private float lifeExpectancy;
	private String localName;
	private String name;
	private int population;
	private String region;
	private float surfaceArea;
	
	@OneToMany(mappedBy="country", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<City> cities;
	
	@OneToMany(mappedBy="country", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Countrylanguage> countrylanguages;
	
	public Country() {
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode2() {
		return this.code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}


	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}


	public float getGnp() {
		return this.gnp;
	}

	public void setGnp(float gnp) {
		this.gnp = gnp;
	}


	public float getGNPOld() {
		return this.GNPOld;
	}

	public void setGNPOld(float GNPOld) {
		this.GNPOld = GNPOld;
	}


	public String getGovernmentForm() {
		return this.governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}


	public String getHeadOfState() {
		return this.headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}


	public short getIndepYear() {
		return this.indepYear;
	}

	public void setIndepYear(short indepYear) {
		this.indepYear = indepYear;
	}


	public float getLifeExpectancy() {
		return this.lifeExpectancy;
	}

	public void setLifeExpectancy(float lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}


	public String getLocalName() {
		return this.localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getPopulation() {
		return this.population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}


	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	public float getSurfaceArea() {
		return this.surfaceArea;
	}

	public void setSurfaceArea(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}


	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}


	public List<Countrylanguage> getCountrylanguages() {
		return countrylanguages;
	}


	public void setCountrylanguages(List<Countrylanguage> countrylanguages) {
		this.countrylanguages = countrylanguages;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
