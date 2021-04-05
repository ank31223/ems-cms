package com.infoobjects.emscms.dto;

public class Client {
	private String id;
	private String companyName;
	private String companyAddress;

	public Client() {
	}

	public Client(String id, String companyName, String companyAddress) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
	}

	@Override
	public String toString() {
		return "Client details are " + "Employee id: " + id + " CompanyName: " + companyName + " CompanyAddress: "
				+ companyAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

}
