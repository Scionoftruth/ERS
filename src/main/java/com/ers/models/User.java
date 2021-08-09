package com.ers.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ers.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler"})
public class User {
	
	@Id
	@Column(name="user_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="first_name", nullable=false)
	private String firstname;
	
	@Column(name="last_name", nullable=false)
	private String lastname;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="u_roles_id")
	private UserRoles role;
	
	@OneToMany(mappedBy="employee",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Reimbursement> employees = new ArrayList<Reimbursement>();
	
	@OneToMany(mappedBy="manager",fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Reimbursement> manager = new ArrayList<Reimbursement>();
	
	
	
	public User() {
		super();
	}

	public User(int id, String firstname, String lastname, String username, String email, String password,
			UserRoles role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	}

	public User(String firstname, String lastname, String username, String email, String password, UserRoles role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	/*
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}*/
	
	

	@Override
	public String toString() {
		return  "\n"+"Id: " + id + "\n" + "First Name: " + firstname + "\n" + "Last Name: " + lastname + "\n" + "Username: " + username
				+ "\n" + "Email: " + email + "\n" + "Password: " + password + "\n"+ "Role: " + role + "\n";
	}
	
	

}
