package com.OAuth2.OAuth2Impl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_crud")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_crud_id")
	private int userId;
	
	@Column(name="user_crud_name")
	private String username;
	
	@Column(name="user_crud_age")
	private int age;
	
	@Column(name="user_crud_salary")
	private float salary;
	
	@Column(name="user_crud_password")
	@JsonIgnore
	private String	 password;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + username + ", age=" + age + ", salary=" + salary + "]";
	}

	public Users() {
		super();
	
	}

	public Users(int userId, String name, int age, float salary, String password) {
		super();
		this.userId = userId;
		this.username = name;
		this.age = age;
		this.salary = salary;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
