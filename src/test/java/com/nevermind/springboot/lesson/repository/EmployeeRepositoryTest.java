package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

    void testFindByFirstNameAndSalary(){
        List<EmployeeEntity> tim = employeeRepository.findByFirstNameAndSalary("Tim", 1000);
        MatcherAssert.assertThat(tim, IsCollectionWithSize.hasSize(1));
    }

}