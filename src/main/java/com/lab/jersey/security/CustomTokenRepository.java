/**
 * 
 */
package com.lab.jersey.security;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

/**
 * @author paolobonansea
 *
 */
@Component("customTokenRepository")
public class CustomTokenRepository implements PersistentTokenRepository  {

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		System.out.println(token);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String token) {
		
		System.out.println(token);
		
		return null;
	}

	@Override
	public void removeUserTokens(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateToken(String arg0, String arg1, Date arg2) {
		// TODO Auto-generated method stub
		
	}

}
