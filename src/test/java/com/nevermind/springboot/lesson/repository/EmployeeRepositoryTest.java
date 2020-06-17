package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.IntegrationTestBase;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

}