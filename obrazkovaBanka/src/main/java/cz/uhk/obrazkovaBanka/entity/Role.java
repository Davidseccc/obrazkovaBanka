package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity

public class Role implements Serializable {
	@Id	@GeneratedValue
	int id;
	@Size(max=20, min=2)
	String name;
	@Size(max=35, min=0)
	String description;
	@OneToMany(mappedBy="role")
	List<User> users = new ArrayList<User>();
	
		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private static final long serialVersionUID = 1L;

	public Role() {
		super();
	}
   
}
