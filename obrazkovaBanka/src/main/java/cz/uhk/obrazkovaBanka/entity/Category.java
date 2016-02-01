package cz.uhk.obrazkovaBanka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@Table(name="Category",uniqueConstraints=@UniqueConstraint(columnNames={"id", "name"}))
public class Category implements Serializable {

	@Id @GeneratedValue
	int id;
	@Column(unique = true)
	String name;
	@OneToMany(mappedBy="category", fetch= FetchType.LAZY)
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", images=" + images + "]";
	}
   
}
