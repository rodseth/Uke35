
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityTested {
    public static void main(String[] args) {
        
        
EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
EntityManager em = emf.createEntityManager();
Customer customer1 = new Customer("Mari", "Haugen");
Customer customer2 = new Customer("George", "Martin");
Customer customer3 = new Customer("Nikolaj", "Clemens");
Customer customer4 = new Customer("Donald", "Duck");
try {
      em.getTransaction().begin();
      em.persist(customer1);
      em.persist(customer2);
      em.persist(customer3);
      em.persist(customer4);
      em.getTransaction().commit();
      //Verify that books are managed and has been given a database id
      System.out.println(customer1.getId());
      System.out.println(customer2.getId());
      System.out.println(customer3.getId());
      System.out.println(customer4.getId());
       	 
    	}finally{
        	em.close();
    	}
    }
    
}
