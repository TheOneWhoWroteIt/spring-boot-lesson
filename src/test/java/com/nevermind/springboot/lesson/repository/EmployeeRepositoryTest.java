package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.dto.EmployeeFilter;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import com.nevermind.springboot.lesson.entity.QEmployeeEntity;
import com.nevermind.springboot.lesson.projection.EmployeeNameView;
import com.nevermind.springboot.lesson.projection.EmployeeNativeView;
import com.nevermind.springboot.lesson.util.QPredicates;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest extends IntegrationTestBase {

    private static final Integer TIM_ID = 1;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void testFindById(){
        Optional<EmployeeEntity> tim = employeeRepository.findById(TIM_ID);
        assertTrue(tim.isPresent());
    }

    @Test
    void testFindByFirstName(){
        Optional<EmployeeEntity> tim = employeeRepository.findByFirstNameContaining("Tim");
        assertTrue(tim.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary(){
        List<EmployeeEntity> tim = employeeRepository.findByFirstNameAndSalary("Tim", 1000);
        assertThat(tim, hasSize(1));
    }

    @Test
    void testFindAllBySalaryGreaterThan(){
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(800);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindAllBySalaryGreaterThanNative(){
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanNative(800);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindByCustomQuery(){
        EmployeeFilter employeeFilter = EmployeeFilter.builder()
                .firstName("tiM")
                .build();

        List<EmployeeEntity> employees = employeeRepository.findByFilter(employeeFilter);
        assertThat(employees, hasSize(1));

    }

    @Test
    void testQuerydslPredicate(){
        BooleanExpression predicate = QEmployeeEntity.employeeEntity.firstName.containsIgnoreCase("tIm")
                .and(QEmployeeEntity.employeeEntity.salary.goe(1000));

        Page<EmployeeEntity> page = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(page.getContent(), hasSize(1));

    }

    @Test
    void testQPredicates(){
        EmployeeFilter employeeFilter = EmployeeFilter.builder()
                .firstName("tiM")
                .Salary(1000)
                .build();

        Predicate predicate = QPredicates.builder()
                .add(employeeFilter.getFirstName(), QEmployeeEntity.employeeEntity.firstName::containsIgnoreCase)
                .add(employeeFilter.getLastName(), QEmployeeEntity.employeeEntity.lastName::containsIgnoreCase)
                .add(employeeFilter.getSalary(), QEmployeeEntity.employeeEntity.salary::goe)
                .buildAnd();

        Iterable<EmployeeEntity> result = employeeRepository.findAll(predicate);

        assertTrue(result.iterator().hasNext());

    }



}