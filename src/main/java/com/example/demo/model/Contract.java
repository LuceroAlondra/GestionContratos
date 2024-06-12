package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_company_relationship")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int not null")
	private int idEmpCom;

	@ManyToOne
	@JoinColumn(name = "employee_id", columnDefinition = "int not null")
	private Employee idEmployee;

	@ManyToOne
	@JoinColumn(name = "company_id", columnDefinition = "int not null")
	private Company companyId;

	@Column(name = "start_date", columnDefinition = "Date not null")
	private Date startDate;

	@Column(name = "until_date", columnDefinition = "Date not null")
	private Date untilDate;

	@Column(name = "role", columnDefinition = "varchar(50) not null")
	private String role;

	public Contract() {
		super();
	}

	public Contract(int idEmpCom, Employee idEmployee, Company companyId, Date startDate, Date untilDate, String role) {
		super();
		this.idEmpCom = idEmpCom;
		this.idEmployee = idEmployee;
		this.companyId = companyId;
		this.startDate = startDate;
		this.untilDate = untilDate;
		this.role = role;
	}

	public int getIdEmpCom() {
		return idEmpCom;
	}

	public void setIdEmpCom(int idEmpCom) {
		this.idEmpCom = idEmpCom;
	}

	public Employee getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
