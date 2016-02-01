package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Role;

@Service
public class RoleDao {

	@PersistenceContext
   // private EntityManager entityManager;
     
	public long countRoles() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        long count = em.createQuery("SELECT COUNT(o) FROM Role o", Long.class).getSingleResult();
        em.close();
        return count;
	}

	public List<Role> findAllRoles() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        List<Role> roles = em.createQuery("SELECT o FROM Role o", Role.class).getResultList();
        em.close();
        return roles;
	}
	
	public Role findRole(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

        if (id == null) return null;
        Role tag = em.find(Role.class, id);
        em.close();
        return tag;
    }
	
	public Role findRoleEagerly(Long id) {

		if (id == null) return null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        Role role =  em.createQuery("SELECT o FROM Role o LEFT OUTER JOIN FETCH o.users WHERE o.id = (:id)", Role.class)
            	.setParameter("id", id).getSingleResult();
        em.close();
        return role;
	}

	public List<Role> findRoleEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		
        List<Role> tags = em.createQuery("SELECT o FROM Role o", Role.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return tags;
	}

	@Transactional
    public void persist(Role r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(r);
		
		em.getTransaction().commit();
		em.close();
    }

	
    public void remove(Role r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        if (em.contains(r)) {
            em.remove(r);
        } else {
            r = findRole(r.getId());
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


    public Role merge(Role r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
        Role merged = em.merge(r);
        em.flush();
        
        em.getTransaction().commit();
        em.close();
        return merged;
    }

}
