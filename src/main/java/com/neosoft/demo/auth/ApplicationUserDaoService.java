package com.neosoft.demo.auth;

import static com.neosoft.demo.security.ApplicationUserRole.STUDENT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.neosoft.demo.security.ApplicationUserRole.*;
import com.google.common.collect.Lists;

@Repository("fake")
public class ApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(STUDENT.getGrantedAuthorities(), 
						passwordEncoder.encode("panwar"), "ankit", true, true, true, true),
				new ApplicationUser(ADMIN.getGrantedAuthorities(), 
						passwordEncoder.encode("panwar"), "linda", true, true, true, true));
		return applicationUsers;
	}
}
