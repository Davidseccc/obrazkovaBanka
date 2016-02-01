package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Rating;

@Service
public class RatingDao {

	@PersistenceContext
   // private EntityManager entityManager;
     
	public long countRatings() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        long count = em.createQuery("SELECT COUNT(o) FROM Rating o", Long.class).getSingleResult();
        em.close();
        return count;
	}

	public List<Rating> findAllRatings() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        List<Rating> ratings = em.createQuery("SELECT o FROM Rating o", Rating.class).getResultList();
        em.close();
        return ratings;
	}


	public Rating findRating(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        if (id == null) return null;
        Rating rating = em.find(Rating.class, id);
        em.close();
        return rating;
    }
	
	public double findAvgRatingbyImageId(Integer imageid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        Double count = em.createQuery("SELECT AVG(o.value) FROM Rating o join o.image WHERE o.image.id = (:imageId)", Double.class)
        		.setParameter("imageId", imageid).getSingleResult();
        em.close();
        if (count == null){
        	count = 0.0;
        }
        return count;
	}

	
	public Rating findRatingsEagerly(Long id) {

		if (id == null) return null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        Rating rating =  em.createQuery("SELECT o FROM Rating o LEFT OUTER JOIN FETCH WHERE o.image_id = (:id)", Rating.class)
            	.setParameter("id", id).getSingleResult();
        em.close();
        return rating;
	}

	public List<Rating> findRatingEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        List<Rating> ratings = em.createQuery("SELECT o FROM Tag o", Rating.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return ratings;
	}

	@Transactional
    public void persist(Rating r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(r);
		
		em.getTransaction().commit();
		em.close();
    }

	
    public void remove(Rating r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        if (em.contains(r)) {
            em.remove(r);
        } else {
            r = findRating(r.getId());
            em.remove(r);
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


    public Rating merge(Rating r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        Rating merged = em.merge(r);
        em.flush();
        
        em.getTransaction().commit();
        em.close();
        return merged;
    }


}
