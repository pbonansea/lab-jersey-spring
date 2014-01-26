/**
 * 
 */
package com.lab.jersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.lab.jersey.exception.ApplicationException;
import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.service.CityService;

/**
 * @author paolobonansea
 * 
 */
@Path("/city")
public class CityResource {

	@Autowired
	private CityService cityService;
	
	/**
	 * url http://localhost:8080/lab-jersey/address/all
	 * 
	 * @return 
	 *         [{"id":1,"postCode":"E2 9NJ","cityId":null},{"id":2,"postCode":"E2 9NJ"
	 *         ,"cityId":null},{"id":3,"postCode":"E2 9NJ","cityId":null}]
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresss() {

		try {
		
			return Response.ok(this.cityService.getAll()).build();

		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

}
