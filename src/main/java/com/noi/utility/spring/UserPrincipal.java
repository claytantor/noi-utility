package com.noi.utility.spring;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * TODO refactor so boolean conversions are not required we are making the
 * boolean types persisted as strings because the ETL is failing on them, this
 * shoudl be fixed later because it isnt as performant.
 * 
 * @author cgraham
 * 
 */
@Deprecated
public class UserPrincipal implements Authentication, UserDetails {

	// id for entity
	private Long id;

	private boolean authenticated;

	// properties
	private java.lang.Integer version = new Integer(0);
	private String username;
	private String password;
	private String email;
	private String userClass;

	private String expiredString = "false";
	private String credentialsExpiredString = "false";
	private String lockedString = "false";
	private String enabledString = "false";

	private String authGuid;

	private Set<Role> roles = new HashSet<Role>();

	public String getAuthGuid() {
		return authGuid;
	}

	public void setAuthGuid(String authGuid) {
		this.authGuid = authGuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Returns the credentialsExpired.
	 */
	public Boolean getCredentialsExpired() {
		return new Boolean(credentialsExpiredString);
	}

	/**
	 * @param credentialsExpired
	 *            The credentialsExpired to set.
	 */
	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpiredString = credentialsExpired.toString();
	}

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return new Boolean(enabledString);
	}

	/**
	 * @param enabled
	 *            The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabledString = enabled.toString();
	}

	/**
	 * @return Returns the expired.
	 */
	public Boolean getExpired() {
		return new Boolean(expiredString);
	}

	/**
	 * @param expired
	 *            The expired to set.
	 */
	public void setExpired(Boolean expired) {
		this.expiredString = expired.toString();
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the locked.
	 */
	public Boolean getLocked() {
		return new Boolean(lockedString);
	}

	/**
	 * @param locked
	 *            The locked to set.
	 */
	public void setLocked(Boolean locked) {
		this.lockedString = locked.toString();
	}

	/**
	 * @return Returns the version.
	 */
	public java.lang.Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getExpiredString() {
		return expiredString;
	}

	public void setExpiredString(String expiredString) {
		this.expiredString = expiredString;
	}

	public String getCredentialsExpiredString() {
		return credentialsExpiredString;
	}

	public void setCredentialsExpiredString(String credentialsExpiredString) {
		this.credentialsExpiredString = credentialsExpiredString;
	}

	public String getLockedString() {
		return lockedString;
	}

	public void setLockedString(String lockedString) {
		this.lockedString = lockedString;
	}

	public String getEnabledString() {
		return enabledString;
	}

	public void setEnabledString(String enabledString) {
		this.enabledString = enabledString;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !Boolean.parseBoolean(this.enabledString);
	}

	@Override
	public boolean isAccountNonLocked() {
		return !Boolean.parseBoolean(this.lockedString);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !Boolean.parseBoolean(this.credentialsExpiredString);
	}

	@Override
	public boolean isEnabled() {
		return Boolean.parseBoolean(this.enabledString);
	}

	@Override
	public GrantedAuthority[] getAuthorities() {
		GrantedAuthority[] authorities = new GrantedAuthority[roles.size()];

		Role[] roleArray = (Role[]) roles.toArray(new Role[roles.size()]);
		for (int i = 0; i < roleArray.length; i++) {
			Role r = roleArray[i];
			GrantedAuthority ga = new GrantedAuthorityImpl(r.getRole());
			authorities[i] = ga;
		}

		return authorities;
	}

	@Override
	public Object getCredentials() {
		return password;
	}

	@Override
	public Object getDetails() {
		return this;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		this.authenticated = arg0;

	}

	@Override
	public String getName() {
		return username;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}
	
	

}
