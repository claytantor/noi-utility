package com.noi.utility.spring;

import java.util.List;
@Deprecated
public interface UserPrincipalDao {
	public void save(UserPrincipal user);
	public void delete(UserPrincipal user);
	public UserPrincipal findByPrimaryKey(Long id);
	public UserPrincipal findByUserName(String username);
	public UserPrincipal findByAuthId(String authId);
	public UserPrincipal findByEmail(String email);
	public List<UserPrincipal> findAll();
	public List<UserPrincipal> findByTwitterIds(Long[] ids);
	
}
