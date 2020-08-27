package com.bosch.employeeemgmt.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int streetNo;
	private String cityName;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private Employee emp;
	
	public Address() {
	}

	public Address(int streetNo, String cityName) {
		super();
		this.streetNo = streetNo;
		this.cityName = cityName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetNo=" + streetNo + ", cityName=" + cityName + "]";
	}

}
