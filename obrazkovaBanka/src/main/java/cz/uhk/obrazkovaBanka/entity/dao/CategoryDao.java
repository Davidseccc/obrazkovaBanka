package cz.uhk.obrazkovaBanka.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.obrazkovaBanka.entity.Category;

@Repository
public class CategoryDao {

	@PersistenceContext
     
	public long countCategories() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
        long count = em.createQuery("SELECT COUNT(o) FROM Category o", Long.class).getSingleResult();
        em.close();
        return count;
    }

	public List<Category> findAllCategories() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		List<Category> categories = em.createQuery("SELECT o FROM Category o order by o.id", Category.class).getResultList();
		em.close();
        return categories;
    }

	public Category findCategory(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (id == null) return null;
        Category category = em.find(Category.class, id);
        em.close();
        return category;
    }
	
	public Category findCategoryByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		Category category = em.createQuery("SELECT o FROM Category o WHERE o.name = (:name)", Category.class)
            	.setParameter("name", name).getSingleResult();
		em.close();
		return category;
	}
	
	public Category findCategoryEagerly(Long id) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		if (id == null) return null;
        Category category =  em.createQuery("SELECT o FROM Category o WHERE o.id = (:id)", Category.class)
            	.setParameter("id", id).getSingleResult();
        em.close();
        return category;
	}

	public List<Category> findCategoryEntries(int firstResult, int maxResults) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
        List<Category> categories = em.createQuery("SELECT o FROM Category o", Category.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
        em.close();
        return categories;
    }

	@Transactional
    public void persist(Category category) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		
		em.persist(category);
		
		em.getTransaction().commit();
		em.close();	
    }

	
    public void remove(Category category) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
        if (em.contains(category)) {
            em.remove(category);
        } else {
            category = findCategory(category.getId());
            em.remove(category);
        }
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

    public Category merge(Category category) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		
		em.getTransaction().begin();
		
        Category merged = em.merge(category);
        em.flush();
		em.getTransaction().commit();
        em.close();
        return merged;
    }

	@SuppressWarnings("unchecked")
	public List<Object> countImagesInCategories() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1");
		EntityManager em = emf.createEntityManager(); 
		List<Object> countImages = em.createNativeQuery("SELECT distinct count(i.id) FROM Category AS c JOIN Image AS i WHERE i.category_id = c.id group by c.id;", Long.class)
            	.getResultList();
		em.close();
		return countImages;
	}



}
