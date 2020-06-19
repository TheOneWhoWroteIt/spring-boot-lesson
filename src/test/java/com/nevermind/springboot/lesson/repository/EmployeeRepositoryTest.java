package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import com.nevermind.springboot.lesson.projection.EmployeeNameView;
import com.nevermind.springboot.lesson.projection.EmployeeNativeView;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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



}