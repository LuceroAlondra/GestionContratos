package com.example.demo.dto;

import java.util.Date;

public class CompanyDTO {
	private int idCompany;
    private String nameCompany;
    private String cityCompany;
    private String countryCompany;
    private String countryCode;
    private String emailCompany;
    private String sector;
    private Date since;
    
	public CompanyDTO() {
		super();
	}

	public CompanyDTO(int idCompany, String nameCompany, String cityCompany, String countryCompany, String countryCode,
			String emailCompany, String sector, Date since) {
		super();
		this.idCompany = idCompany;
		this.nameCompany = nameCompany;
		this.cityCompany = cityCompany;
		this.countryCompany = countryCompany;
		this.countryCode = countryCode;
		this.emailCompany = emailCompany;
		this.sector = sector;
		this.since = since;
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
