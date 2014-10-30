package se.chas.projecttemplate.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import se.chas.projecttemplate.dao.GenericDAOImpl;
import se.chas.projecttemplate.dao.core.EMF;
import se.chas.projecttemplate.dao.impl.CityDAO;
import se.chas.projecttemplate.dao.impl.CountryDAO;
import se.chas.projecttemplate.data.models.City;
import se.chas.projecttemplate.data.models.Country;
import se.chas.projecttemplate.utils.Utilities;
import se.chas.projecttemplate.web.filter.Filter;

public class CountryServiceImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2744056454355251379L;
	private GenericDAOImpl<Country, String> countryDao;
	private GenericDAOImpl<City, Integer> cityDao;

	public CountryServiceImpl() {
		countryDao = new CountryDAO();
		cityDao = new CityDAO();
	}
	
	public List<Country> findCountriesPaginated(Integer maxResults, Integer firstResult) {
		return countryDao.findAllWithPaging(maxResults, firstResult, "Country.findAll");
	}
	
	public List<Country> findCountriesPaginatedFiltered(Filter filter, Integer maxResults, Integer firstResult) {
		
		EntityManager em = EMF.createEntityManager();
		List<Country> countries = new ArrayList<Country>();
		
		try {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Country> q = cb.createQuery(Country.class);
			Root<Country> c = q.from(Country.class);
			
			ParameterExpression<String> continent = null;
			ParameterExpression<String> region = null;
			ParameterExpression<String> country = null;
			ParameterExpression<String> countryCode = null;
			
			if (!Utilities.stringIsNullOrEmpty(filter.getContinent())) {
				continent = cb.parameter(String.class);
				q.select(c).where(cb.equal(c.get("continent"), continent));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getCountry())) {
				country = cb.parameter(String.class);
				q.select(c).where(cb.equal(c.get("name"), country));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getCountryCode())) {
				countryCode = cb.parameter(String.class);
				q.select(c).where(cb.equal(c.get("code"), countryCode));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getRegion())) {
				region = cb.parameter(String.class);
				q.select(c).where(cb.equal(c.get("region"), region));
			}
			
			q.orderBy(cb.asc(c.get("name")));
			
			TypedQuery<Country> query = em.createQuery(q).setFirstResult(firstResult).setMaxResults(maxResults);
			
			if(continent != null)
				query.setParameter(continent, filter.getContinent());
			
			if(country != null)
				query.setParameter(country, filter.getCountry());
			
			if(countryCode != null)
				query.setParameter(countryCode, filter.getCountryCode());
			
			if(region != null)
				query.setParameter(region, filter.getRegion());
			
			countries = query.getResultList();
			
		} catch (Exception e) {
			// log exception
		} finally {
			em.close();
		}
		
		return countries;
	}
	
	public Integer countAllCountries() {
		return countryDao.countAll().intValue();
	}
	
	public Integer countAllPaginatedFiltered(Filter filter) {

		EntityManager em = EMF.createEntityManager();
		Long result = new Long(0);
		
		try {
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> q = cb.createQuery(Long.class);
			Root<Country> root = q.from(Country.class);
			q.select(cb.count(root));
			
			ParameterExpression<String> continent = null;
			ParameterExpression<String> region = null;
			ParameterExpression<String> country = null;
			ParameterExpression<String> countryCode = null;
			
			if (!Utilities.stringIsNullOrEmpty(filter.getContinent())) {
				continent = cb.parameter(String.class);
				q.where(cb.equal(root.get("continent"), continent));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getCountry())) {
				country = cb.parameter(String.class);
				q.where(cb.equal(root.get("name"), country));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getCountryCode())) {
				countryCode = cb.parameter(String.class);
				q.where(cb.equal(root.get("code"), countryCode));
			}
			
			if (!Utilities.stringIsNullOrEmpty(filter.getRegion())) {
				region = cb.parameter(String.class);
				q.where(cb.equal(root.get("region"), region));
			}
			
			TypedQuery<Long> query = em.createQuery(q);
			
			if(continent != null)
				query.setParameter(continent, filter.getContinent());
			
			if(country != null)
				query.setParameter(country, filter.getCountry());
			
			if(countryCode != null)
				query.setParameter(countryCode, filter.getCountryCode());
			
			if(region != null)
				query.setParameter(region, filter.getRegion());
			
			result = query.getSingleResult();
			
		} catch (Exception e) {
			// log exception
		} finally {
			em.close();
		}
		
		return result.intValue();
		
	}
	
	public List<City> findCitiesPaginated(Integer maxResults, Integer firstResult) {
		return cityDao.findAllWithPaging(maxResults, firstResult, "City.findAll");
	}
	
	public Integer countAllCities() {
		return cityDao.countAll().intValue();
	}
}
