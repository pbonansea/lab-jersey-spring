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
import com.lab.jersey.model.Company;

/**
 * @author paolobonansea
 *
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	private static Logger LOG = Logger.getLogger(CompanyService.class);

	protected EntityManager entityManager;
	 
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	/* (non-Javadoc)
	 * @see com.lab.jersey.service.CompanyService#getAll()
	 */
	@Override
	public List<Company> getAll() throws ServiceException {

		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			CriteriaQuery<Company> criteriaQuery = criteriaBuilder
					.createQuery(Company.class);
			Root<Company> criteria = criteriaQuery.from(Company.class);
			CriteriaQuery<Company> all = criteriaQuery.select(criteria);
	        TypedQuery<Company> allQuery = getEntityManager().createQuery(all);
	        
	        return allQuery.getResultList();

		} catch (Exception ex) {
			LOG.error("error company get all: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

}
