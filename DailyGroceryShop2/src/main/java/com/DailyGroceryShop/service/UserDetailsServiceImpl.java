package com.DailyGroceryShop.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.DailyGroceryShop.domain.Role;
import com.DailyGroceryShop.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserService userSevice;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("Inside UserDetails " + userName);
		User user = userSevice.findUserByUserName(userName);
		Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
		Set<Role> rolesSet = user.getRoles();
		for(Role role : rolesSet) {
			grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), grantedAuthoritySet);
	}

}
