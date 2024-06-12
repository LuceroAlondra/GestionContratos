package com.example.demo.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int not null")
	private int idCompany;

	@Column(name = "name", columnDefinition = "varchar(100) not null")
	private String nameCompany;

	@Column(name = "city", columnDefinition = "varchar(50)")
	private String cityCompany;

	@Column(name = "country", columnDefinition = "varchar(50) not null")
	private String countryCompany;

	@Column(name = "country_code", columnDefinition = "varchar(10) not null")
	private String countryCode;

	@Column(name = "email", columnDefinition = "varchar(100) not null")
	private String emailCompany;

	@Column(name = "sector", columnDefinition = "varchar(50) not null")
	private String sector;

	@Column(name = "since", columnDefinition = "Date not null")
	private Date since;

	public Company(String nameCompany, String cityCompany, String countryCompany, String countryCode,
			String emailCompany, String sector, Date since) {
		super();
		this.nameCompany = nameCompany;
		this.cityCompany = cityCompany;
		this.countryCompany = countryCompany;
		this.countryCode = countryCode;
		this.emailCompany = emailCompany;
		this.sector = sector;
		this.since = since;
	}

	public Company() {
		super();
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public String getCityCompany() {
		return cityCompany;
	}

	public void setCityCompany(String cityCompany) {
		this.cityCompany = cityCompany;
	}

	public String getCountryCompany() {
		return countryCompany;
	}

	public void setCountryCompany(String countryCompany) {
		this.countryCompany = countryCompany;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmailCompany() {
		return emailCompany;
	}

	public void setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}
}