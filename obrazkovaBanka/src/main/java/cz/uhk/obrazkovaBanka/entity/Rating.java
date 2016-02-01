package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Rating
 *
 */
@Entity
@Table(name="Rating")
public class Rating implements Serializable {
	@Id	@GeneratedValue
	int id;
	int value;
	Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)	@JoinColumn(name="image_id")
	Image image;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rating [id=" + id + ", value=" + value + ", date=" + date + ", image=" + image + "]";
	}

	
	public Rating() {
		super();
	}
   
}
