package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import com.nevermind.springboot.lesson.projection.EmployeeNameView;
import com.nevermind.springboot.lesson.projection.EmployeeNativeView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, EmployeeCustomRepository {

    Optional<EmployeeEntity> findByFirstNameContaining(String firstName);

    List<EmployeeEntity> findByFirstNameAndSalary(String firstName, Integer salary);

    List<EmployeeNameView> findAllBySalaryGreaterThan(Integer salary);

    @Query(value = "select " +
            "e.id as id, " +
            "e.first_name || e.last_name as fullName " +
            "from employee e " +
            "where e.salary > :salary", nativeQuery = true)
    List<EmployeeNativeView> findAllBySalaryGreaterThanNative(@Param("salary") Integer salary);
}
