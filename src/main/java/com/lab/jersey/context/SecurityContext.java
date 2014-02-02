/**
 * 
 */
package com.lab.jersey.context;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.lab.jersey.model.User;
import com.lab.jersey.security.Role;

/**
 * @author paolobonansea
 *
 */
public class SecurityContext implements javax.ws.rs.core.SecurityContext  {

	private final User user;
    private final Session session;
	
    public SecurityContext(Session session, User user) {
        this.session = session;
        this.user = user;
    }
    
	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isSecure() {
		return (null != session) ? session.isSecure() : false;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (null == session || !session.isActive()) {
            // Forbidden
            Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build();
            throw new WebApplicationException(denied);
        }
 
        try {
            // this user has this role?
            return true; // user. getRoles().contains(Role.class);
        } catch (Exception e) {
        }
         
        return false;
	}


}
