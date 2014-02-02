/**
 * 
 */
package com.lab.jersey.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.lab.jersey.context.SecurityContext;
import com.lab.jersey.context.Session;
import com.lab.jersey.exception.ServiceException;
import com.lab.jersey.model.User;
import com.lab.jersey.service.UserService;

/**
 * @author paolobonansea
 *
 */
public class SecurityContextFilter implements ContainerRequestFilter {
	
	@Autowired
	protected UserService userService;

	@Override
	public void filter(ContainerRequestContext request) throws IOException {

		final String sessionId = request.getHeaderString("session-id");
		 
        User user = null;
        Session session = null;
 
        if (sessionId != null && sessionId.length() > 0) {
            // Load session object from repository
//            session = sessionRepository.findOne(sessionId);
             
            // Load associated user from session
            if (null != session) {
                try {
					user = userService.getById(Long.valueOf(session.getUserId()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ServiceException e) {
					e.printStackTrace();
				}
            }
        }
 
        // Set security context
        request.setSecurityContext(new SecurityContext(session, user));
		
	}
	
}
