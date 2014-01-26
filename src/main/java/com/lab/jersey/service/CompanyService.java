/**
 * 
 */
package com.lab.jersey.service;

import java.util.List;

import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.Company;

/**
 * @author paolobonansea
 *
 */
public interface CompanyService {

	public List<Company> getAll() throws ServiceException;

}
