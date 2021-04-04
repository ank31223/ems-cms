package com.infoobjects.emscms.dto;

public class Employees {

	private String id;
	private String name;
	private String gender;
	private int age;
	private int contactNo;
	private String email;
	private String designation;
	private int salary;
	private int status;

	public Employees() {
		// TODO Auto-generated constructor stub
	}

	public Employees(String id, String name, String gender, int age, int contactNo, String email, String designation,
			int salary, int status) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.contactNo = contactNo;
		this.email = email;
		this.designation = designation;
		this.salary = salary;
		this.status = status;
	}

//	public Employee(String id, String name, int age, int contactNo, String email) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.age = age;
//		this.contactNo = contactNo;
//		this.email = email;
//	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Employee Detaills:- " + id + " " + name + "  " + gender + " " + age + " " + contactNo+" "+email+" "+ designation+" "+salary+" "+status+" ";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	



}
