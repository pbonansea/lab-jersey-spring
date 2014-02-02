/**
 * 
 */
package com.lab.jersey.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import com.lab.jersey.exception.ApplicationException;
import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.User;
import com.lab.jersey.service.CityService;
import com.lab.jersey.service.CompanyService;
import com.lab.jersey.service.UserService;

/**
 * @author paolobonansea
 * 
 */
@Path("/user")
public class UserResource {

	@Autowired
	private UserService userService;
		
	@Autowired
	private CityService cityService;

	@Autowired
	private CompanyService companyService;

	@Context
    SecurityContext securityContext;
	
	@GET
    public String getUserPrincipal() {
        return securityContext.getUserPrincipal().getName();
    }
	
	/**
	 * url http://localhost:8080/lab-jersey/user/create
	 * "Content-Type: application/json"
	 * 
	 * @param user
	 *            {"lastName":"last name 1","name":"test name 1","idAddress":"1"
	 *            }
	 * @return
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User user) {

		try {

			userService.create(user);

			return Response.ok(user, MediaType.APPLICATION_JSON).build();
						
		} catch (NumberFormatException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/update
	 * "Content-Type: application/json"
	 * 
	 * @param user
	 *            { "id": "1",
	 *            "lastName":"last name 1","name":"test name 1","idAddress":"1"}
	 * @return
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User user) {

		try {

			userService.update(user);

			return Response.ok(user, MediaType.APPLICATION_JSON).build();

		} catch (NumberFormatException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/delete/1
	 * 
	 * @param id
	 * @return response status, 200, 204, 404....
	 */
	@POST
	@Path("/delete/{id}")
	public Response delete(@PathParam("id") Long id) {

		try {

			userService.delete(id);

			return Response.ok("<status>success</status>").build();
			
		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();			
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/1/version/2.1
	 * 
	 * @param id
	 *            user id
	 * @return 
	 *         {"idAddress":"1","lastName":"last name 1","name":"test name 1","id"
	 *         :"1"}
	 */
	@GET
	@Path("{id}{version:(/version/[^/]+)?}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("id") Long id, @PathParam("version") String version) {
		
		try {
			
			if (!version.equals("")) {
				System.out.println(version.split("/")[2]);
			}
			
			User user = userService.getById(id);

			return Response.ok(user).build();			
			
		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/all
	 * 
	 * @return 
	 *         {"user":[{"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"0"},
	 *         {"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"2"}]}
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	// @Secured("ROLE_ADMIN")
	// @PreAuthorize("hasRole('ROLE_DUMMY')")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Response getUsers() {

		try {

			return Response.ok(userService.getAll()).build();			

		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/city/4
	 * 
	 * @param cityId
	 *            city id
	 * @return 
	 *         {"user":[{"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"0"},
	 *         {"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"2"}]}
	 */
	@GET
	@Path("/city/{cityId}")
	@Produces(MediaType.APPLICATION_JSON)
	@PreAuthorize("hasRole('ROLE_USER')")
	public Response getUsersByCountryId(@PathParam("cityId") Long cityId) {

		try {
		
			return Response.ok(userService.getByCityId(cityId)).build();
			
		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();			
		}
	}

	/**
	 * url http://localhost:8080/lab-jersey/user/city/1/company/4
	 * 
	 * @param cityId
	 *            city id
	 * @param companyId
	 *            company id
	 * @return 
	 *         {"user":[{"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"0"},
	 *         {"idAddress":"1","lastName":"last name 1","name":"test name 1"
	 *         ,"id":"2"}]}
	 */
	@GET
	@Path("/city/{cityId}/company/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	// @PreAuthorize("hasRole('ROLE_DUMMY')")
	public Response getUsersByCityIdCompanyId(@Context SecurityContext sc,
			@PathParam("cityId") Long cityId,
			@PathParam("companyId") Long companyId) {

		try {

			System.out.println(cityService.getByd(cityId).getName());
			System.out.println(companyService.getById(companyId).getName());
			
			System.out.println(sc.isSecure());
			
			return Response.ok(userService.getByCityIdCompanyId(cityId, companyId)).build();

		} catch (ServiceException ex) {
			return new ApplicationException(ex.getMessage()).getResponse();
		}
	}

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured("permitAll")
	public Response login() {
		
		System.out.println("login");
		
		return Response.ok().build();

		
	}
	
}
