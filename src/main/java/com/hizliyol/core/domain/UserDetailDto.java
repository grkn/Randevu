package com.hizliyol.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.hizliyol.core.entity.SchoolResponsible;

public class UserDetailDto extends User implements UserDetails,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237928495560287863L;
	private String firstName;
	private String lastName;
	private String email;
	private Set<SchoolResponsible> schoolResponsibleSet;
	
	public UserDetailDto(User user,String firstName,String lastName,String email,Set<SchoolResponsible> schoolResponsibles) {
		super(user.getUsername(), user.getPassword(), user.getAuthorities());
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.schoolResponsibleSet = schoolResponsibles;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<SchoolResponsible> getSchoolResponsibleSet() {
		return schoolResponsibleSet;
	}

	public void setSchoolResponsibleSet(Set<SchoolResponsible> schoolResponsibleSet) {
		this.schoolResponsibleSet = schoolResponsibleSet;
	}

}
