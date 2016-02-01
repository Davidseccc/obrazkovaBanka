package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity
@Table(name="Comment",uniqueConstraints=@UniqueConstraint(columnNames={"id"}))
public class Comment implements Serializable {
	@Id	@GeneratedValue
	int id;
	Date date;
	@Size(max=255, min=2)
	String content;
	@ManyToOne @JoinColumn(name="user_id")
	User user;
	@ManyToOne	@JoinColumn(name="image_id")
	Image image;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", date=" + date + ", content=" + content + ", user=" + user + ", image=" + image
				+ "]";
	}
	
	
   
}
