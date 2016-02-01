package cz.uhk.obrazkovaBanka.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonBackReference;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: image
 *
 */
@Entity
@Table(name="Image",uniqueConstraints=@UniqueConstraint(columnNames={"id", "hash","deleteHash"}))
public class Image implements Serializable {
		
	@Id
	@GeneratedValue
	int id;
	String name;
	@Column(unique = true)
	String hash;
	Date uploadDate;
	@Size(max=1000, min=0)
	String description;
	long fileSize;
	String fileType;
	
	boolean publicFile;
	String path; 
	String thumbnailPaht;
	@Column(unique = true)
	String deleteHash;
	
	@JsonBackReference	@ManyToOne @JoinColumn(name="user_id") 
	User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	Category category;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { PERSIST, MERGE })
	@JoinTable
	List<Tag> tags = new ArrayList<Tag>();

	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = REMOVE)
	List<Rating> ratings = new ArrayList<Rating>();

	@OneToMany(mappedBy = "image", fetch = EAGER, cascade = { REMOVE, PERSIST })
	List<Comment> comments = new ArrayList<Comment>();

	public Image() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long l) {
		this.fileSize = l;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isPublicFile() {
		return publicFile;
	}

	public void setPublicFile(boolean publicFile) {
		this.publicFile = publicFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeleteHash() {
		return deleteHash;
	}

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the thumbnailPaht
	 */
	public String getThumbnailPaht() {
		return thumbnailPaht;
	}

	/**
	 * @param thumbnailPaht the thumbnailPaht to set
	 */
	public void setThumbnailPaht(String thumbnailPaht) {
		this.thumbnailPaht = thumbnailPaht;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}



	private static final long serialVersionUID = 1L;




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", hash=" + hash + ", uploadDate=" + uploadDate + ", description="
				+ description + ", fileSize=" + fileSize + ", fileType=" + fileType + ", publicFile=" + publicFile
				+ ", path=" + path + ", thumbnailPaht=" + thumbnailPaht + ", deleteHash=" + deleteHash + ", user="
				+ user + ", category=" + category + ", tags=" + tags + ", ratings=" + ratings + ", comments=" + comments
				+ "]";
	}

	public void resizeImage(String path) {
		System.out.println("resizing");
		try {
			Thumbnails.of(path)
			.size(200, 200)
			.toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
