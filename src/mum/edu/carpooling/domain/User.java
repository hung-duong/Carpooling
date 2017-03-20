package mum.edu.carpooling.domain;

import java.util.Date;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
    private Address addresse;
    private UserCredentials userCredentials;
    
	public User() {
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

	public Address getAddresse() {
		return addresse;
	}

	public void setAddresse(Address addresse) {
		this.addresse = addresse;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
}
