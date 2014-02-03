/**
 * 
 */
package com.lab.jersey.context;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.lab.jersey.model.User;
import com.lab.jersey.security.Session;

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
            // forbidden
            Response denied = Response.status(Response.Status.FORBIDDEN)
            		.entity("permission Denied").build();
            throw new WebApplicationException(denied);
        }
 
        try {
            return true; // user. getRoles().contains(Role.class);
        } catch (Exception e) {
        }
         
        return false;
	}


}
