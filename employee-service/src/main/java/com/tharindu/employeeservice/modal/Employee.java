package com.tharindu.employeeservice.modal;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	@OneToOne(cascade = CascadeType.ALL)
	Address address;
	@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
	List<Telephone> telephone;
	
	@ManyToMany(cascade = CascadeType.ALL/* In order to control transactions*/)
	@JoinTable(name="employee_project" ,joinColumns =  @JoinColumn(name = "eid" , referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="did" , referencedColumnName = "id"))
	List<Project> projects;
	
	@Transient
	Allocation[] allocation;
	

	public Allocation[] getAllocation() {
		return allocation;
	}
	public void setAllocation(Allocation[] allocation) {
		this.allocation = allocation;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Telephone> getTelephone() {
		return telephone;
	}
	public void setTelephone(List<Telephone> telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address address) {
		this.address=address;
	}

}
