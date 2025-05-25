package project_poo;
public class User {
	private String name;
	private String surname;
	private int id;
	public User() {
		name="";
		surname="";
		id=000000000;
	}
	public String getName() {
		  return name;
	}
	public void setName(String name) {
		  this.name=name;
	}
	public String getSurname() {
		  return name;
	}
	public void setSurname(String surname) {
		  this.surname=surname;
	}
	public int getId() {
		  return id;
	}
	public void setId(int id) {
		  this.id=id;
	}
}