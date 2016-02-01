package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity implementation class for Entity: Tag
 *
 */
@Entity
@Table(name="Tag",uniqueConstraints=@UniqueConstraint(columnNames={"id", "name"}))
public class Tag implements Serializable {
	@Id @GeneratedValue
	int id;
	@Column(unique = true)
	String name;
	@ManyToMany(mappedBy="tags", fetch = FetchType.LAZY)
	List<Image> images = new ArrayList<Image>();
	
	
	private static final long serialVersionUID = 1L;

	public Tag() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Image> images) {
		this.images = images;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", images=" + images + "]";
	}
   
}
