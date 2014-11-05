package college.courses.data;

import college.courses.exceptions.InvalidDataException;

// No two professors may have the same name 
// Assignment 4: include field that maps onto primary key in database

public class Professor {
	private String firstName;
	private String lastName;

	public Professor( String firstName, String lastName)
			throws InvalidDataException {
		setFirstName(firstName);
		setLastName(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws InvalidDataException {
		if (firstName == null || firstName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a first name");
		}
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws InvalidDataException {
		if (lastName == null || lastName.isEmpty()) {
			throw new InvalidDataException("Proressor must have a last name");
		}
		this.lastName = lastName;
	}
	public String toString() {
		return firstName + " " + lastName;
	}
	
	// Assignement 4: include field that maps onto primary key in database
	public boolean equals( Professor p) {
		  if( ! p.getFirstName().equals(this.getFirstName() ) ) {
			  	return false;
		  }
		  if ( ! p.getLastName().equals(this.getLastName() ) ) {
			  return false;
		  }
		  return true;
	}

}
