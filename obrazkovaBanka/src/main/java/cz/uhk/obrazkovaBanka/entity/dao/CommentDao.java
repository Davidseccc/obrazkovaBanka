package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Comment;

@Service
public class CommentDao {

	@PersistenceContext
   // private EntityManager entityManager;
     
	public long countComments() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        long count = em.createQuery("SELECT COUNT(o) FROM Comment o", Long.class).getSingleResult();
        em.close();
        return count;
	}

	public List<Comment> findAllComments() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        List<Comment> comments = em.createQuery("SELECT o FROM Comment o", Comment.class).getResultList();
        em.close();
        return comments;
	}

	public Comment findComment(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        if (id == null) return null;
        Comment comment = em.find(Comment.class, id);
        em.close();
        return comment;
    }
	
	public Comment findCommentEagerly(Long id) {

		if (id == null) return null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        Comment comment =  em.createQuery("SELECT o FROM Comment o LEFT OUTER JOIN FETCH o.image WHERE o.id = (:id)", Comment.class)
            	.setParameter("id", id).getSingleResult();
        em.close();
        return comment;
	}

	public List<Comment> findCommentEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        List<Comment> comments = em.createQuery("SELECT o FROM Category o", Comment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return comments;
	}

	@Transactional
    public void persist(Comment c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(c);
		
		em.getTransaction().commit();
		em.close();
    }

	
    public void remove(Comment c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        if (em.contains(c)) {
            em.remove(c);
        } else {
            c = findComment(c.getId());
            em.remove(c);
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


    public Comment merge(Comment c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        Comment merged = em.merge(c);
        em.flush();
        
        em.getTransaction().commit();
        em.close();
        return merged;
    }

}
