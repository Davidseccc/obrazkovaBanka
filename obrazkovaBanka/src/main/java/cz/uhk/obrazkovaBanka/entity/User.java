package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonManagedReference;
/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="User",uniqueConstraints=@UniqueConstraint(columnNames={"id", "nickName","email"}))
public class User implements Serializable {
	

	@Id @GeneratedValue
	int id;
	
	String name;
	@Size(max=20, min=4)
	@NotNull 
	@Column(unique = true)
	String nickName;
	@NotNull
	@Column(unique = true)
	String email;
	@NotNull 
	@Size(max=100, min=5)
	String password;
	Date registeredDate;
	Date lastVisit;
	@ManyToOne @JoinColumn(name="role_id")
	Role role;
	
	@OneToMany(mappedBy="user")
	List<Comment> comments = new ArrayList<Comment>();
	
	@JsonManagedReference @OneToMany(mappedBy="user")
	List<Image> images = new ArrayList<Image>();
	
	
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickName=" + nickName + ", email=" + email + ", password="
				+ password + ", registeredDate=" + registeredDate + ", lastVisit=" + lastVisit + ", comments="
				+ comments + "]";
	}


	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
   
}
