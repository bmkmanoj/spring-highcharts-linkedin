
package com.adp.linkedin.schema;

public class Location {
	public String description;
	public boolean isHeadquarters;
	public boolean isActive;
	public Address address;
	public String name;
	public String postalCode;
	
	public String getDescription() {
		return description;
	}

	public boolean isHeadquarters() {
		return isHeadquarters;
	}

	public boolean isActive() {
		return isActive;
	}

	public Address getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getPostalCode() {
		return postalCode;
	}
}
