package main.java.ua.nure.kn.petrenko_vitaliy.usermanagment;

import java.util.Calendar; 
import java.util.Date; 

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	public User(String firstName, String lastName, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}
	
	public User(Long id, String firstName, String lastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}
	
	public User() {
		//STUB
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Date getDateOfBirthd() {
		return dateOfBirth;
	}
	
	public void setDateOfBirthd(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getFullName() {
		return getLastName()+", "+getFirstName();
	}
	
	public int getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(getDateOfBirthd());
		int year = calendar.get(Calendar.YEAR);
		
		return currentYear-year;
	}
	
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (this == obj) {
	        return true;
	    }
	    if (this.getId() == null && ((User) obj).getId() == null) {
	        return true;
	    }
	    return this.getId().equals(((User) obj).getId());
	}
	
	public int hashCode() {
	    if (this.getId() == null) {
	        return 0;
	    }
	    return this.getId().hashCode();
	}
}
