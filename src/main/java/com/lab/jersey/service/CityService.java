/**
 * 
 */
package com.lab.jersey.service;

import java.util.List;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.City;

/**
 * @author paolobonansea
 *
 */
public interface CityService {

	public List<City> getAll() throws ServiceException;
	
}
