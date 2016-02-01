package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Tag;

@Service
public class TagDao {

	@PersistenceContext
   // private EntityManager entityManager;
     
	public long countTags() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        long count = em.createQuery("SELECT COUNT(o) FROM Tag o", Long.class).getSingleResult();
        em.close();
        return count;
	}

	public List<Tag> findAllTags() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        List<Tag> tags = em.createQuery("SELECT o FROM Tag o", Tag.class).getResultList();
        em.close();
        return tags;
	}

	public List<Tag> findTagsByImageId(int imageId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		@SuppressWarnings("unchecked")
		List<Tag> tags = em.createNativeQuery("SELECT t.id, t.name FROM Tag AS t, Image_Tag AS it WHERE (t.id = it.tags_id) AND (it.images_id = (:id));", Tag.class)
        		.setParameter("id", imageId).getResultList();
		//List<Tag> tags = em.createQuery("SELECT distinct t FROM Tag t join t.images WHERE t.images = (:id)", Tag.class).setParameter("id", imageId).getResultList();
        em.close();
        return tags;
	}
	
	public Tag findTag(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        if (id == null) return null;
        Tag tag = em.find(Tag.class, id);
        em.close();
        return tag;
    }
	
	public Tag findTagsEagerly(Long id) {

		if (id == null) return null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        Tag tag =  em.createQuery("SELECT o FROM Tag o LEFT OUTER JOIN FETCH o.imageId WHERE o.id = (:id)", Tag.class)
            	.setParameter("id", id).getSingleResult();
        em.close();
        return tag;
	}

	public List<Tag> findTagsEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        List<Tag> tags = em.createQuery("SELECT o FROM Tag o", Tag.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return tags;
	}

	@Transactional
    public void persist(Tag t) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(t);
		
		em.getTransaction().commit();
		em.close();
    }

	
    public void remove(Tag t) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        if (em.contains(t)) {
            em.remove(t);
        } else {
            t = findTag(t.getId());
            em.remove(t);
        }
        em.getTransaction().commit();
        em.close();
    }

	
    public void flush() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
        
		em.flush();
        
        em.close();
    }

	
    public void clear() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		em.clear();
		
		em.close();
    }


    public Tag merge(Tag c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        Tag merged = em.merge(c);
        em.flush();
        
        em.getTransaction().commit();
        em.close();
        return merged;
    }

}
