/**
 * Incoming Student Data Request.
 * 
 *
 */

package com.softwaretesting.testing.dto.inbound;

import com.softwaretesting.testing.model.Customer;

import javax.validation.constraints.NotBlank;

public class CustomerInDTO {
	@NotBlank
	private String userName;
	@NotBlank
	private String name;
	@NotBlank
	private String phoneNumber;

	
	public CustomerInDTO() {
	}
	
	public Customer toEntity() {
		Customer customer = new Customer();

		customer.setUserName(userName);
		customer.setName(name);
		customer.setPhoneNumber(phoneNumber);
		
		return customer;
	}

	public String getUserName() {return userName;}

	public void setUserName(String userName) {this.userName = userName;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
