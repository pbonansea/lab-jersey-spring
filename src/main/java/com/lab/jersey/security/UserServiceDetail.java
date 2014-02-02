/**
 * 
 */
package com.lab.jersey.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lab.jersey.service.UserService;

/**
 * @author paolobonansea
 *
 */
@Service("userServiceDetail")
public class UserServiceDetail implements UserDetailsService {

	@Autowired
	private UserService userService;
	
    @PostConstruct
    public void init() {
    	System.out.println("init");
    }

	@Override
	public User loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		User user = new User();
		
		user.setUsername(username);
		user.setPassword("123");
		
		Role role = new Role();
		if (username.equals("user")) {
			role.setName("ROLE_USER");			
		} else {
			if (username.equals("company")) {
				role.setName("ROLE_COMPANY");			
			} else {
				if (username.equals("city")) {
					role.setName("ROLE_CITY");			
				}
			}
		}

		List<Role> authorities = new ArrayList<Role>();
		authorities.add(role);
		
		user.setAuthorities(authorities);
		
		return user;
	}

}
