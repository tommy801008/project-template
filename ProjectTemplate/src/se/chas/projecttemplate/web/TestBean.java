package se.chas.projecttemplate.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import se.chas.projecttemplate.dao.GenericDAOImpl;
import se.chas.projecttemplate.dao.impl.CountryDAO;
import se.chas.projecttemplate.data.models.Country;

@ManagedBean
@RequestScoped
public class TestBean {

	private GenericDAOImpl<Country, String> dao;
	
	private List<Country> countries;
	
	public TestBean() {
		setCountries(new ArrayList<Country>());
		
		dao = new CountryDAO();
		
		setCountries(dao.findAll());
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	
}
