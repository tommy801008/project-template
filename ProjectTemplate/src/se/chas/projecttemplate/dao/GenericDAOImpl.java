package se.chas.projecttemplate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import se.chas.projecttemplate.dao.core.EMF;


public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7941962712350546417L;

	@SuppressWarnings("unchecked")
	public  GenericDAOImpl(){
		ParameterizedType genericSuperClass = (ParameterizedType)getClass().getGenericSuperclass();
		this.entityClass = (Class<T>)genericSuperClass.getActualTypeArguments()[0];
	}
		   
	
	protected Class<T> entityClass;
	
	public Class<T> getEntityClass(){
		return entityClass;
	}
		
		/*
	 * {@inheritDoc} 
	 */
	@Override
	public T findById(ID id) {
		EntityManager em = EMF.createEntityManager();
		final T result;
		try {
			result = em.find(entityClass, id);
		} finally {
			em.close(); 
		}
		
		return result;
	}
	/**
	 * {@inheritDoc}
	 * [Select * FROM Entity]
	 */
	@Override
	public List<T> findAll() {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
			TypedQuery<T> q = em.createQuery(cq);
			results = q.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(final String queryName) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
			Query q = em.createNamedQuery(queryName);
			results = (List<T>) q.getResultList();
		}
		finally {
			em.close();
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findSingle(final String queryName) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		T t;
		try {
			Query q = em.createNamedQuery(queryName);
			q.setMaxResults(1);
			results = (List<T>)q.getResultList();
			if (results.isEmpty()) {
				return null;
			}
			 t= results.get(0);	
		}
		finally {
			em.close();
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findSingle(final String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		T t;
		try {
			Query q = em.createNamedQuery(queryName);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i + 1, params[i]);
			}
			q.setMaxResults(1);
			results = (List<T>)q.getResultList();
			if (results.isEmpty()) {
				return null;
			}
			t = results.get(0);
		}
		finally {
			em.close();
		}
		return t;
	}
	
	@Override
	public List<T> findAll(int maxResults) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
			TypedQuery<T> q = em.createQuery(cq);
			q.setMaxResults(maxResults);
			results = q.getResultList();
			//Thread.sleep(6000);			
		} finally {
			em.close(); 
		}
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQueryWithMaximum(int maxResults, String queryName) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
			Query q = em.createNamedQuery(queryName);
			q.setMaxResults(maxResults);
			results = (List<T>)q.getResultList();
		}
		finally {
			em.close();
		}
		return results;
	}
	
	@Override
	public List<T> findByNamedQueryAndParamsWithMaximum(int maxResults, String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
		
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			query.setMaxResults(maxResults);
			results = (List<T>)query.getResultList();
		}
		finally {
			em.close();
		}
		return results;
	}
	
	@Override
	public List<T> findByNamedQueryAndParamsWithMaximumAndMinimum(int maxResults, int firstResult, String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
		
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResult);
			results = (List<T>)query.getResultList();
		}
		finally {
			em.close();
		}
		return results;
	}
	
	@Override
	public List<T> findAllFiltered(final String queryName, final Object... params) {
		EntityManager em = EMF.createEntityManager();
		
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				if(params[i] instanceof Number)
					query.setParameter(i + 1, params[i]);
				else
					query.setParameter(i + 1, "%" + ((String)params[i]).replace(' ', '%') + "%");
			}
			
			results =  query.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
		
	@Override
	public List<T> findAllFilteredWithPaging(int maxResults, int firstResult, final String queryName, final Object... params) {
		EntityManager em = EMF.createEntityManager();
		
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResult);
			results =  query.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
	
	@Override
	public List<T> findAllFilteredWithPagingNative(final String queryName, final Object[] params) {
		EntityManager em = EMF.createEntityManager();
		
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			
			results =  query.getResultList();
		} finally {
			em.close();
		}
		
		return results;
	}
	@Override
	public List<T> findAllWithPaging(int maxResults, int firstResult, final String queryName){
		EntityManager em = EMF.createEntityManager();
		
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResult);
			results = query.getResultList();
		} finally {
			em.close();
		}
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findByNamedQuery(final String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			results =  query.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findByNamedQueryAndNamedParams(String queryName,
			Map<String, ? extends Object> params) {
		EntityManager em = EMF.createEntityManager();
		final List<T> results;
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (Map.Entry<String, ? extends Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
			results =  query.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
	
	/**
	 * {@inheritDoc}
	 * [Select ]
	 */
	@Override
	public Long countAll() {
		EntityManager em = EMF.createEntityManager();
		TypedQuery<Long> q;
		Long l = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cq = cb.createQuery(Long.class);
			Root<T> root = cq.from(entityClass);
			cq.select(cb.count(root));
			q = em.createQuery(cq);
			l = q.getSingleResult();
		} finally {
			em.close();
		}
		
		return l;
	}
	
	@Override
	public Long countAllByNamedQuery(String queryName) {
		EntityManager em = EMF.createEntityManager();
		TypedQuery<Long> q;
		
		Long l = null;
		try {
			q = em.createNamedQuery(queryName, Long.class);
			l = q.getSingleResult();
		} finally {
			em.close();
		}

		return l;
	}
	
	@Override
	public Long countAll(String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		TypedQuery<Long> q;
		Long l = null;
		try {
			q = em.createNamedQuery(queryName, Long.class);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i + 1, params[i]);
			}
			l = q.getSingleResult();
		} finally {
			em.close();
		}

		return l;
	}
	
	@Override
	public Long countAllFiltered(String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		Number result = 0L;
		try {
			TypedQuery<Number> query = em.createNamedQuery(queryName, Number.class);
			for (int i = 0; i < params.length; i++) {
				if(params[i] instanceof Number)
					query.setParameter(i+1, params[i]);
				else
					query.setParameter(i + 1, "%" + ((String)params[i]).replace(' ', '%') + "%");
			}
			result = query.getSingleResult();
		} finally {
			em.close();
		}

		return (Long)result;
	}
	
	@Override
	public Integer countAllInteger(String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		TypedQuery<Integer> q;
		Integer r = null;
		try {
			q = em.createNamedQuery(queryName, Integer.class);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i + 1, params[i]);
			}
			r = q.getSingleResult();
		} finally {
			em.close();
		}

		return r;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> countSlaDowntime(final String queryName, Object... params) {
		
		EntityManager em = EMF.createEntityManager();
		final List<Long> result;
		try {
			Query query = em.createNamedQuery(queryName);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			//query.setFirstResult(0);
			result = query.getResultList();
		} finally {
			em.close(); 
		}
		
		return result;
	}
	
	@Override
	public Long sumByNamedQuery(String queryName) {
		EntityManager em =EMF.createEntityManager();
		Long result;
		try {
			Query q = em.createNamedQuery(queryName);
			//List<Object> r = q.getResultList();
			Object queryResult = q.getSingleResult();
			if (queryResult == null)
				result = 0L;
			else
				result = (Long)queryResult;
		}
		finally {
				em.close();
		}
		return result;
	}
	
	
	@Override
	public Long sumByNamedQueryWithParams(String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		TypedQuery<Long> q;
		Long l = null;
		try {
			q = em.createNamedQuery(queryName, Long.class);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i + 1, params[i]);
			}
			l = q.getSingleResult();
		} finally {
			em.close();
		}

		return l;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> sumsByNamedQuery(String queryName) {
		EntityManager em = EMF.createEntityManager();
		List<Long> results = new java.util.ArrayList<Long>();
		try {
			Query q = em.createNamedQuery(queryName);
			List<Object[]> obj1 = q.getResultList();
			Object[] obj2 = obj1.get(0);
			for(Object l : obj2)
			{
				results.add((Long)l);
			}
		}
		finally {
			em.close();
		}
		return results;
	}
	
	public boolean isManaged(){
		return EMF.createEntityManager().contains(getEntityClass());
	}
	
	@Override
	public void save(T entity) {

		EntityManager em = EMF.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		 try {
			 transaction.begin();
			 em.persist(entity);
			 transaction.commit();
		} catch (Exception e) {

			transaction.rollback();
		} 
		finally {
			em.close();
		}
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T entity) {
		
		EntityManager em = EMF.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			//Merge the entity to the manager
			T mergedEntity = em.merge(entity);
			em.remove(mergedEntity);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			em.close();
		} 
		
	}
	
	@Override
	public void voidByNamedQuery(final String queryName, Object... params) {
		
		EntityManager em = EMF.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			transaction.begin();
			query.executeUpdate();
			transaction.commit();
		} finally {
			em.close(); 
		}
	}
	

	@Override
	public T update(T entity) {
		EntityManager em = EMF.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		T updated = null;
		try {
			transaction.begin();
			updated = em.merge(entity);
			transaction.commit();
		} catch (Exception e) {

			transaction.rollback();
		} finally {
			em.close();
		}
		 
		return updated;
	}
	
	public int updateByNamedQuery(final String queryName, Object... params) {

		EntityManager em = EMF.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		int updated = 0;
		try {
			transaction.begin();
			Query query = em.createNamedQuery(queryName);
			for(int i = 0; i < params.length; i++){
				query.setParameter(i+1, params[i]);
			}
			updated = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {

			transaction.rollback();
		} finally {
			em.close();
		}
		return updated;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNativeQuery(final String queryName, Object... params) {
		EntityManager em = EMF.createEntityManager();
		final List<T> results;
		try {
			Query query = em.createNativeQuery(queryName, getEntityClass());
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			results =(List<T>) query.getResultList();
		} finally {
			em.close(); 
		}
		
		return results;
	}
	
	@Override
	public BigDecimal sum(String nativeQuery, Object... params) {
		EntityManager em = EMF.createEntityManager();
		Query query = em.createNativeQuery(nativeQuery);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		Object result = query.getSingleResult();
		em.close();
		return (BigDecimal)result;
	}
	
	@Override
	public List<T> findByNamedQueryWithMaximumAndMinimum(int firstResult, int maxResults, String queryName) {
		EntityManager em = EMF.createEntityManager();
		List<T> results;
		try {
		
			TypedQuery<T> query = em.createNamedQuery(queryName, getEntityClass());
			query.setMaxResults(maxResults);
			query.setFirstResult(firstResult);
			results = (List<T>)query.getResultList();
		}
		finally {
			em.close();
		}
		return results;
	}

}
