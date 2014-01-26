/**
 * 
 */
package com.lab.jersey.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author paolobonansea
 *
 */
@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "city")  
public class City implements Serializable {
	
	@XmlElement
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@XmlElement
	private String name;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
		
}
