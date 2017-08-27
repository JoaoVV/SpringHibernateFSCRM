package com.grimer.customertracker.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	@NotNull(message="is required")
	@Size(min=1, max=30, message="is required")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull(message="is required")
	@Size(min=1, max=30, message="is required")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="age")
	private int age;
	
	@Min(value=1, message="age must be equal or higher than 1")
	@Max(value=200, message="No one has lived that long!")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name="country")
	private String country;
	
	@Transient
	private Map<String, String> countryOptions;

	public Customer() {
		countryOptions = new LinkedHashMap<>();
		initCountryOptions();
	}

	//To Refactor: Put into proper Countries DB table
	private void initCountryOptions() {
		if(countryOptions != null) {
			countryOptions.put("PT", "Portugal");
			countryOptions.put("ES", "Spain");
			countryOptions.put("FR", "France");
			countryOptions.put("DE", "Germany");
			countryOptions.put("US", "USA");
			countryOptions.put("BR", "Brasil");
			countryOptions.put("PL", "Poland");
			countryOptions.put("UK", "England");
		}
		
	}

	public Map<String, String> getCountryOptions() {
		return countryOptions;
	}

	public int getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
		
}





