package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.User;

@Controller
public class UserDao {

	@PersistenceContext

	public long countUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		long count = em.createQuery("SELECT COUNT(o) FROM User o", Long.class).getSingleResult();

		em.close();
		return count;
	}

	public List<User> findAllUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		List<User> users = em.createQuery("SELECT o FROM USER o", User.class).getResultList();

		em.close();
		return users;
	}

	public User findUser(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		if (id == null)
			return null;
		User user = em.find(User.class, id);
		em.close();
		return user;
	}

	public List<User> findUserEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		List<User> users = em.createQuery("SELECT o FROM USER o", User.class).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		em.close();
		return users;
	}

	@Transactional
	public void persist(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(user);

		em.getTransaction().commit();
		em.close();
	}

	@Transactional
	public void remove(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		if (em.contains(user)) {
			em.remove(user);
		} else {
			User attached = findUser(user.getId());
			em.remove(attached);

		}

		em.getTransaction().commit();
		em.close();
	}

	@Transactional
	public void flush() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		em.flush();

		em.getTransaction().commit();
		em.close();
	}

	@Transactional
	public void clear() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();

		em.clear();

		em.getTransaction().commit();
		em.close();
	}

	@Transactional
	public User merge(User customer) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		User merged = em.merge(customer);
		em.flush();

		em.getTransaction().commit();
		em.close();
		return merged;
	}

	public TypedQuery<User> findUserByNameEquals(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("The name argument is required");
		TypedQuery<User> q = em.createQuery("SELECT o FROM User AS o WHERE o.name = :name", User.class);
		q.setParameter("name", name);
		
		//em.close();
		return q;
	}
	
	public TypedQuery<User> findUserByEmailEquals(String email) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		if (email == null || email.length() == 0)
			throw new IllegalArgumentException("The Email argument is required");
		TypedQuery<User> q = em.createQuery("SELECT o FROM User AS o WHERE o.email = :email", User.class);
		q.setParameter("email", email);
		
		//em.close();
		return q;
	}

	public TypedQuery<User> findUserByNickNameEquals(String nickName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		if (nickName == null || nickName.length() == 0)
			throw new IllegalArgumentException("The Email argument is required");
		TypedQuery<User> q = em.createQuery("SELECT o FROM User AS o WHERE o.nickName = :nickName", User.class);
		q.setParameter("nickName", nickName);
		
		//em.close();
		return q;
	}

}
