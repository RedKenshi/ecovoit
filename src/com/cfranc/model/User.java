package com.cfranc.model;

public class User {
	
	private static long countId=0;
	
	private long iD;
	private String name;
	private String email;
	private String pwd;
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	
	public User(String name, String email, String pwd) {
		super();
		this.iD = ++countId;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}
}
