package se.chas.projecttemplate.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import se.chas.projecttemplate.data.models.City;
import se.chas.projecttemplate.data.models.Country;
import se.chas.projecttemplate.paginator.Paginator;
import se.chas.projecttemplate.services.CountryServiceImpl;
import se.chas.projecttemplate.web.filter.FilterManager;

@ManagedBean
@ViewScoped
public class CountryBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7927112206819032234L;
	
	private CountryServiceImpl countryService;
	private List<Country> countries; 
	private List<City> cities;
	private FilterManager filterManager;
	
	//Paginator specific
	private Paginator paginator;
	
	public CountryBean() {
		countryService = new CountryServiceImpl();
	}
	
	@PostConstruct
	private void init() {
		
		initialisePagination();
		filterManager = new FilterManager();
		
		if (isCountry()) {
			setCountries(new ArrayList<Country>());
		} else if (isCity()) {
			setCities(new ArrayList<City>());
		}
		
		findCountriesOrCities();
	}
	
	private void findCountriesOrCities() {
		if (isCountry()) {
			setCountries(countryService.findCountriesPaginated(paginator.getNumberToGet(), paginator.getOffset()));
			paginator.setRecordsTotal(countryService.countAllCountries());
			paginator.updatePages();
		} else if (isCity()) {
			setCities(countryService.findCitiesPaginated(paginator.getNumberToGet(), paginator.getOffset()));
			paginator.setRecordsTotal(countryService.countAllCities());
			paginator.updatePages();
		}
	}
	
	public void reset() {
		init();
	}
	
	public void findCountriesOrCitiesFiltered() {
		setCountries(countryService.findCountriesPaginatedFiltered(filterManager.getFilter(), paginator.getNumberToGet(), paginator.getOffset()));
		countFilteredCountries();
	}
	
	private void countFilteredCountries() {
		paginator.setRecordsTotal(countryService.countAllPaginatedFiltered(filterManager.getFilter()));
		paginator.updatePages();
	}
	
	private void initialisePagination() {
		setPaginator(new Paginator(0, 25));
	}
	
	public void firstPage() {
		paginator.first();
		findCountriesOrCitiesFiltered();
		countFilteredCountries();
	}
	
	public void nextPage() {
		paginator.next();
		findCountriesOrCitiesFiltered();
		countFilteredCountries();
	}
	
	public void prevPage() {
		paginator.prev();
		findCountriesOrCitiesFiltered();
		countFilteredCountries();
	}
	
	public void lastPage() {
		paginator.last();
		findCountriesOrCitiesFiltered();
		countFilteredCountries();
	}
	
	private boolean isCountry() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/countries/all_countries.xhtml");
	}
	
	private boolean isCity() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/cities/all_cities.xhtml");
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public FilterManager getFilterManager() {
		return filterManager;
	}

	public void setFilterManager(FilterManager filterManager) {
		this.filterManager = filterManager;
	}
}
