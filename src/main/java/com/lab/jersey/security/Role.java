/**
 * 
 */
package com.lab.jersey.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author paolobonansea
 *
 */
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3102416621935592220L;
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
	
}
