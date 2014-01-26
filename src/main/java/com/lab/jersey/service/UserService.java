/**
 * 
 */
package com.lab.jersey.service;

import java.util.List;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.User;

/**
 * @author paolobonansea
 *
 */
public interface UserService {

	public void create(User user) throws ServiceException;

	public void update(User user) throws ServiceException;
	
	public void delete(long id) throws ServiceException;
	
	public User getById(long id) throws ServiceException;
	
	public List<User> getAll() throws ServiceException;
	
	public List<User> getByCityId(long cityId) throws ServiceException;

	public List<User> getByCityIdCompanyId(long cityId, long companyId) throws ServiceException;

}
