package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Image;

@Controller
public class ImageDao {

	@PersistenceContext

	public List<Image> findImageByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (name == null) throw new IllegalArgumentException("The featured argument is required");
        TypedQuery<Image> q = em.createQuery("SELECT o FROM Image AS o WHERE o.name like :name", Image.class);
        q.setParameter("name", "%"+name + "%");
        
        List<Image> images =  q.getResultList();
        em.close();
        return images;
    }
	
	public Image findImageByHash(String hash) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (hash == null) throw new IllegalArgumentException("The hash argument is required");
        TypedQuery<Image> q = em.createQuery("SELECT o FROM Image AS o WHERE o.hash = :hash", Image.class);
        q.setParameter("hash", hash);
        
        Image image =  q.getSingleResult();
        em.close();
        return image;
    }
	
	public Image findImageByDeleteHash(String hash) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (hash == null) throw new IllegalArgumentException("The hash argument is required");
        TypedQuery<Image> q = em.createQuery("SELECT o FROM Image AS o WHERE o.deleteHash = :hash", Image.class);
        q.setParameter("hash", hash);
        
        Image image =  q.getSingleResult();
        em.close();
        return image;
	}

	public long countImages() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        long count = em.createQuery("SELECT COUNT(o) FROM Image o", Long.class).getSingleResult();
        em.close();
        return count;
	}

	public List<Image> findAllImages() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		List<Image> images = em.createQuery("SELECT o FROM Image o", Image.class).getResultList();
		em.close();
		return images;
	}

	public Image findImage(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (id == null) return null;
        Image image = em.find(Image.class, id);
        em.close();
        return image;
    }

	public List<Image> findImageEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		List<Image> images =  em.createQuery("SELECT o FROM Image o", Image.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return images;
        
	}
	
	@Transactional
	public void persist(Image image) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(image);
		em.getTransaction().commit();
		em.close();
	}
	
    @Transactional
	public void remove(Image i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        if (em.contains(i)) {
            em.remove(i);
        } else {
            Image attached = findImage(i.getId());
            em.remove(em.getReference(Image.class, attached.getId()));
        }
        em.getTransaction().commit();
        em.close();
    }

	public void flush() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        em.flush();
		em.getTransaction().commit();
        em.close();
    }

	public void clear() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        em.clear();
		em.getTransaction().commit();
        em.close();
    }

	public Image merge(Image i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		
        Image merged = em.merge(i);
        em.flush();
		em.getTransaction().commit();
        em.close();
        return merged;
    }

	public List<Image> findImagesWithTag(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (name == null) throw new IllegalArgumentException("The featured argument is required");
        TypedQuery<Image> q = em.createQuery("Select distinct o from Image o join o.tags t where t.name = :name", Image.class)
        		.setParameter("name", name);
       
        
        List<Image> images =  q.getResultList();
        em.close();
        return images;
	}





}
