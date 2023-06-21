package com.example.SpringJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {
    private final EntityManager em;
    public List<Employee> findAllBySimpleQuery(
            String firstname,
            String lastname,
            String email
    ){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        //select * from Employee
        Root<Employee> root = criteriaQuery.from(Employee.class);
        //prepare where clause
        //WHERE firstname like '%ahmad%'
        Predicate firstnamePredicate = criteriaBuilder
                .like(root.get("firstname"),"%" + firstname + "%");
        //WHERE firstname like '%rahimi%'
        Predicate lastnamePredicate = criteriaBuilder
                .like(root.get("lastname"),"%" + lastname + "%");
        //WHERE firstname like '%ahmad@gmail.com%'
        Predicate emailPredicate = criteriaBuilder
                .like(root.get("email"),"%" + email + "%");
        Predicate firstnameOrlastnamePredicate = criteriaBuilder.or(
                firstnamePredicate,
                lastnamePredicate
        );
        //=> final query => select * from employee where firstname like '%ahmad%'
        //=> or lastname like '%rahimi%' and email like '%ahmad@gmail.com%'
        var andEmailPredicate = criteriaBuilder.and(firstnameOrlastnamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    public List<Employee> findAllByOptionalQuery(
            SearchRequest request
    ){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        //select * from employee
        Root<Employee> root = criteriaQuery.from(Employee.class);
        if(request.getFirstname() != null){
            Predicate firstnamePredicate = criteriaBuilder
                    .like(root.get("firstname"), "%" + request.getFirstname() + "%");
            predicates.add(firstnamePredicate);
        }
        if(request.getLastname() != null){
            Predicate lastnamePredicate = criteriaBuilder
                    .like(root.get("lastname"), "%" + request.getLastname() + "%");
            predicates.add(lastnamePredicate);
        }
        if(request.getEmail() != null){
            Predicate emailPredicate = criteriaBuilder
                    .like(root.get("email"), "%" + request.getEmail() + "%");
            predicates.add(emailPredicate);
        }
        criteriaQuery.where(
                criteriaBuilder.or(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
