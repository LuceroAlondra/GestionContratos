package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id", columnDefinition = "int not null")
	private int employeeId;

	@Column(name = "name", columnDefinition = "varchar(50) not null")
	private String nameEmployee;

	@Column(name = "lastname", columnDefinition = "varchar(50) not null")
	private String lastName;

	@Column(name = "city", columnDefinition = "varchar(50) not null")
	private String cityEmployee;

	@Column(name = "country", columnDefinition = "varchar(50) not null")
	private String countryEmployee;

	@Column(name = "country_code", columnDefinition = "varchar(10) not null")
	private String countryCodeEmployee;

	@Column(name = "email", columnDefinition = "varchar(100) not null")
	private String emailEmployee;

	@Column(name = "role", columnDefinition = "varchar(50) not null")
	private String role;

	@Column(name = "birth_date", columnDefinition = "Date not null")
	private Date birthDate;

	public Employee() {
		super();
	}

	public Employee(String nameEmployee, String lastName, String cityEmployee, String countryEmployee,
			String countryCodeEmployee, String emailEmployee, String role, Date birthDate) {
		super();
		this.nameEmployee = nameEmployee;
		this.lastName = lastName;
		this.cityEmployee = cityEmployee;
		this.countryEmployee = countryEmployee;
		this.countryCodeEmployee = countryCodeEmployee;
		this.emailEmployee = emailEmployee;
		this.role = role;
		this.birthDate = birthDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getNameEmployee() {
		return nameEmployee;
	}

	public void setNameEmployee(String nameEmployee) {
		this.nameEmployee = nameEmployee;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCityEmployee() {
		return cityEmployee;
	}

	public void setCityEmployee(String cityEmployee) {
		this.cityEmployee = cityEmployee;
	}

	public String getCountryEmployee() {
		return countryEmployee;
	}

	public void setCountryEmployee(String countryEmployee) {
		this.countryEmployee = countryEmployee;
	}

	public String getCountryCodeEmployee() {
		return countryCodeEmployee;
	}

	public void setCountryCodeEmployee(String countryCodeEmployee) {
		this.countryCodeEmployee = countryCodeEmployee;
	}

	public String getEmailEmployee() {
		return emailEmployee;
	}

	public void setEmailEmployee(String emailEmployee) {
		this.emailEmployee = emailEmployee;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", nameEmployee=" + nameEmployee + ", lastName=" + lastName
				+ ", cityEmployee=" + cityEmployee + ", countryEmployee=" + countryEmployee + ", countryCodeEmployee="
				+ countryCodeEmployee + ", emailEmployee=" + emailEmployee + ", role=" + role + ", birthDate="
				+ birthDate + "]";
	}

}
