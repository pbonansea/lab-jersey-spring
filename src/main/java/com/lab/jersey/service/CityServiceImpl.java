/**
 * 
 */
package com.lab.jersey.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.City;

/**
 * @author paolobonansea
 *
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

	private static Logger LOG = Logger.getLogger(CityService.class);

	protected EntityManager entityManager;
	 
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	/* (non-Javadoc)
	 * @see com.lab.jersey.service.CityService#getAll()
	 */
	@Override
	public List<City> getAll() throws ServiceException {

		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			CriteriaQuery<City> criteriaQuery = criteriaBuilder
					.createQuery(City.class);
			Root<City> criteria = criteriaQuery.from(City.class);
			CriteriaQuery<City> all = criteriaQuery.select(criteria);
	        TypedQuery<City> allQuery = getEntityManager().createQuery(all);
	        
	        return allQuery.getResultList();

		} catch (Exception ex) {
			LOG.error("error city get all: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

}
