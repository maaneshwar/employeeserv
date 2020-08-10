package com.paypal.bfs.test.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "line1", nullable = false, length = 50)
	@Size(min = 2, max = 35, message = "Address Line 1 should have atleast 2 characters")
	private String line1;

	@Column(name = "line2")
	private String line2;

	@Column(name = "city", nullable = false, length = 50)
	@Size(min = 2, max = 35, message = "City should have atleast 2 characters")
	private String city;

	@Column(name = "stateCd", nullable = false, length = 2)
	private String stateCd;

	@Column(name = "zipCode", nullable = false, length = 10)
	@Size(min = 5, message = "Zip code should have atleast 5 characters")
	private String zipCode;

	public Address(Integer id, String line1, String line2, String city, String stateCd, String zipCode) {
		super();
		this.id = id;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.stateCd = stateCd;
		this.zipCode = zipCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
