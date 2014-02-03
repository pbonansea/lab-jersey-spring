/**
 * 
 */
package com.lab.jersey.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.hibernate.HibernateUtil;
import com.lab.jersey.model.User;

/**
 * @author paolobonansea
 * 
 */
@Repository
@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger LOG = Logger.getLogger(UserService.class);

	@Override
	public void create(User user) throws ServiceException {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();				
			}
			LOG.error("error user create: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		} 
	}

	@Override
	public void update(User user) throws ServiceException {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();				
			}
			LOG.error("error user update: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void delete(long id) throws ServiceException {

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			User user = this.getById(id);
			if (user != null) {
				tx = session.beginTransaction();
				session.delete(user);
				tx.commit();				
			}
		} catch (HibernateException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();				
			}
			LOG.error("error user delete: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		} 
	}

	@Override
	public User getById(long id) throws ServiceException {

		Session session = null;
		try {

			session = HibernateUtil.getSession();
			return (User) session.get(User.class, id);

		} catch (HibernateException ex) {
			LOG.error("error user get by id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		} 
	}

	@Override
	public List<User> getAll() throws ServiceException {
		
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			Criteria criteria = session.createCriteria(User.class);

			return criteria.list();

		} catch (HibernateException ex) {
			LOG.error("error user get all: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<User> getByCityId(long cityId) throws ServiceException {
		
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			Criteria criteria = session.createCriteria(User.class).add(
					Restrictions.eq("cityId", cityId));

			return criteria.list();

		} catch (HibernateException ex) {
			LOG.error("error user get by city id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		} 
	}

	@Override
	public List<User> getByCityIdCompanyId(long cityId, long companyId) throws ServiceException {

		Session session = null;
		try {

			session = HibernateUtil.getSession();

			Criteria criteria = session.createCriteria(User.class).add(
					Restrictions.eq("cityId", cityId))
					.add(Restrictions.eq("companyId", companyId));

			return criteria.list();

		} catch (HibernateException ex) {
			LOG.error("error user get by city id and company id: " + ex.getMessage(), ex);
			throw new ServiceException(ex);
		}
	}

}