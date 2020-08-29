
package facades;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import jpa_entity.Employee;



public class EmployeeFacade {
    
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;

    private EmployeeFacade() {}

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public Employee createEmployee(String name, String address, BigDecimal salary){
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        }finally {
            em.close();
        }
    }
    
    public Employee getEmployeeById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,id);
            return employee;
        }finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesByName(String name){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e from Employee e where e.name = :name ",Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Employee> getAllEmployees(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select e from Employee e",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public <List>Employee getEmployeesWithHighestSalary(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select MAX(e.salary) FROM Employee e",Employee.class);
            return (Employee) query.getResultList();
        }finally {
            em.close();
        }
    }
        
    }
           
