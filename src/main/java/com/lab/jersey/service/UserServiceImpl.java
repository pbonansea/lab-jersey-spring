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
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.User;

/**
 * @author paolobonansea
 * 
 */
@Repository
@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger LOG = Logger.getLogger(UserService.class);

	protected EntityManager entityManager;
	 
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Transactional
	@Override
	public void create(User user) throws ServiceException {

    	try {

    		getEntityManager().persist(user);
    		
    	} catch (Exception ex) {
			LOG.error("error user create: " + ex.getMessage(), ex);
			throw new HibernateException(ex);
    	}
	}

    @Transactional
	@Override
	public void update(User user) throws ServiceException {

    	try {
    	
        	getEntityManager().merge(user);

    	} catch (Exception ex) {
			LOG.error("error user update: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
    	}
	}

    @Transactional
	@Override
	public void delete(long id) throws ServiceException {

		try {

			User user = this.getById(id);
			if (user != null) {
				getEntityManager().remove(user);
			}	

		} catch (Exception ex) {
			LOG.error("error user delete: " + ex.getMessage(), ex);
			throw new ServiceException(ex);			
		}
	}

	@Override
	public User getById(long id) throws ServiceException {

		try {

			return getEntityManager().find(User.class, id);

		} catch (Exception ex) {
			LOG.error("error user get by id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<User> getAll() throws ServiceException {
		
		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			CriteriaQuery<User> criteriaQuery = criteriaBuilder
					.createQuery(User.class);
			Root<User> criteria = criteriaQuery.from(User.class);
			CriteriaQuery<User> all = criteriaQuery.select(criteria);
	        TypedQuery<User> allQuery = getEntityManager().createQuery(all);
	        
	        return allQuery.getResultList();
			
		} catch (Exception ex) {
			LOG.error("error user get all: " + ex.getMessage(), ex);
			throw new ServiceException(ex);	
		}		
	}

	@Override
	public List<User> getByCityId(long cityId) throws ServiceException {
		
		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			CriteriaQuery<User> criteriaQuery = criteriaBuilder
					.createQuery(User.class);
			Root<User> criteria = criteriaQuery.from(User.class);
			criteriaQuery.where(
					criteriaBuilder.equal(criteria.get("cityId"), cityId));

			return getEntityManager().createQuery(criteriaQuery).getResultList();

		} catch (Exception ex) {
			LOG.error("error user get by city id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);	
		}
	}

	@Override
	public List<User> getByCityIdCompanyId(long cityId, long companyId) throws ServiceException {

		try {

			CriteriaBuilder criteriaBuilder = getEntityManager()
					.getCriteriaBuilder();

			CriteriaQuery<User> criteriaQuery = criteriaBuilder
					.createQuery(User.class);
			Root<User> criteria = criteriaQuery.from(User.class);
			criteriaQuery.where(
					criteriaBuilder.equal(criteria.get("cityId"), cityId),
					criteriaBuilder.equal(criteria.get("companyId"), companyId));

			return getEntityManager().createQuery(criteriaQuery).getResultList();

		} catch (Exception ex) {
			LOG.error("error user get by city id and company id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);	
		}
	}

}
