package com.noi.utility.spring;

import java.util.List;
import java.util.Set;
@Deprecated
public interface UserPrincipalService {
	public Long saveUserPrincipal(UserPrincipal up) throws UserPrincipalServiceException;
	public void deleteUserPrincipal(UserPrincipal up) throws UserPrincipalServiceException;
	public UserPrincipal createUserPrincipal();
	public boolean authenticate(String username, String password) throws UserPrincipalServiceException,UserNotFoundException;
	public UserPrincipal loadUser(String username) throws UserPrincipalServiceException,UserNotFoundException;
	public UserPrincipal loadUserByAuthId(String authId) throws UserPrincipalServiceException,UserNotFoundException;
	public UserPrincipal loadUserEmail(String authId) throws UserPrincipalServiceException,UserNotFoundException;
	
	public void saveUserPrincipalRoles(UserPrincipal up, Set<Role> roles) throws UserPrincipalServiceException;
	public void deleteUserPrincipalRole(UserPrincipal up, Role role) throws UserPrincipalServiceException;
	
	//twitter stuff
	public List<UserPrincipal> findUsersByTwitterIds(Long[] twitterids) throws UserPrincipalServiceException;
	
	
}
