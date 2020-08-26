package com.employee.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.employee.entity.EmployeeModel;
import com.employee.entity.TeamModel;

public class EmployeeMain {
	 private static EntityManagerFactory entityManagerFactory =
	           Persistence.createEntityManagerFactory("my-unit"); 
	public static void main(String[] args) {
        try {
            persistEmployees();
            EmployeeData();
            findByTeam();
            findByTeams();
            findByTeamAndLevel();
        } finally {
            entityManagerFactory.close();
        }

	}
	private static void EmployeeData() {
		System.out.println("Empoyees data sorted by teams ");
    	EntityManager em = entityManagerFactory.createEntityManager();
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	CriteriaQuery<EmployeeModel> query = criteriaBuilder.createQuery(EmployeeModel.class);
    	Root<EmployeeModel> employee = query.from(EmployeeModel.class);
	    CriteriaQuery<EmployeeModel> selectquery = query.select(employee);
	    selectquery.orderBy(criteriaBuilder.asc(employee.get("team")));
	   TypedQuery<EmployeeModel> typedQuery1 = em.createQuery(selectquery);
	   List<EmployeeModel> resultlist = typedQuery1.getResultList();
	   resultlist.forEach(System.out::println);
       em.close();
}
	//Predicate -IN
    private static void findByTeam() {
        System.out.println("Empoyees in Backend and UI Team");
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeModel> query = criteriaBuilder.createQuery(EmployeeModel.class);
        Root<EmployeeModel> employee = query.from(EmployeeModel.class);
        query.select(employee).where(employee.get("team").in("UI", "Backend"));
        TypedQuery<EmployeeModel> typedQuery = em.createQuery(query);
        List<EmployeeModel> resultList = typedQuery.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }
    
    //Nested Predicate - OR & AND 
	private static void findByTeamAndLevel() {
		System.out.println("Employees with Team= UI or Backend & position");
		EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeModel> query = criteriaBuilder.createQuery(EmployeeModel.class);
        Root<EmployeeModel> employee = query.from(EmployeeModel.class);
        Predicate predicateForUITeam = criteriaBuilder.equal(employee.get("team"), "UI");
        Predicate predicateForBackendTeam = criteriaBuilder.equal(employee.get("team"), "Backend");
        Predicate predicateForTeam = criteriaBuilder.or(predicateForUITeam, predicateForBackendTeam);
        
        Predicate predicateForSalary = criteriaBuilder.equal(employee.get("position"), "E3");
        Predicate predicateForInfo = criteriaBuilder.and(predicateForTeam,predicateForSalary);
        query.where(predicateForInfo);
        List<EmployeeModel> resultList = em.createQuery(query).getResultList();
        resultList.forEach(System.out::println);
        em.close();
	}
	
	//Predicate -Not IN
    private static void findByTeams() {
        System.out.println("Empoyees in not in E1 and E4");
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<EmployeeModel> query = criteriaBuilder.createQuery(EmployeeModel.class);
        Root<EmployeeModel> employee = query.from(EmployeeModel.class);
        query.select(employee).where(employee.get("position").in("E1", "E4").not());
        TypedQuery<EmployeeModel> typedQuery = em.createQuery(query);
        List<EmployeeModel> resultList = typedQuery.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }

    public static void persistEmployees() {
    	EmployeeModel e1 = EmployeeModel.create("Tanya", "Backend", "E4");
    	EmployeeModel e2 = EmployeeModel.create("Gulpreet", "UI", "E2");
    	EmployeeModel e3 = EmployeeModel.create("Aryan", "UI", "E3");
    	EmployeeModel e4 = EmployeeModel.create("Surabhi", "Backend", "E3");
    	EmployeeModel e5 = EmployeeModel.create("Shiv", "Testing", "E3");
    	EmployeeModel e6 = EmployeeModel.create("Shivani", "Testing", "E4");
    	TeamModel t1 = TeamModel.create("UI","Pro1","Sneha");
    	TeamModel t2 = TeamModel.create("Backend","Pro2","Trasha");
    	TeamModel t3 = TeamModel.create("Testing","Pro3","Manish");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.persist(e4);
        em.persist(e5);
        em.persist(e6);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.getTransaction().commit();
        em.close();
    }
}
