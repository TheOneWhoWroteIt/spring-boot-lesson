package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByFirstNameContaining(String firstName);

    List<EmployeeEntity> findByFirstNameAndSalary(String firstName, Integer salary);
}
