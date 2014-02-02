/**
 * 
 */
package com.lab.jersey.context;

import java.io.Serializable;
import java.util.Date;

/**
 * @author paolobonansea
 *
 */
public class Session implements Serializable {

	private static final long serialVersionUID = -7483170872690892182L;
    
    private String sessionId;   // id
    private String userId;      // user
    private boolean active;     // session active?
    private boolean secure;     // session secure?
 
    private Date createTime;    // session create time
    private Date lastAccessedTime;  // session last use time
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return the secure
	 */
	public boolean isSecure() {
		return secure;
	}
	/**
	 * @param secure the secure to set
	 */
	public void setSecure(boolean secure) {
		this.secure = secure;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the lastAccessedTime
	 */
	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}
	/**
	 * @param lastAccessedTime the lastAccessedTime to set
	 */
	public void setLastAccessedTime(Date lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
    
}
