package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	@Id
	private String email;
	private String password;

	@Transient
	private List<Phone> phones = new ArrayList<Phone>();
	private boolean isAdmin;

	public User() {
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> list) {
		this.phones = list;
	}


	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
