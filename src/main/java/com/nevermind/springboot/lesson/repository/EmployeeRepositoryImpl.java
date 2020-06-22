package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.dto.EmployeeFilter;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;
import com.nevermind.springboot.lesson.entity.QEmployeeEntity;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findByFilter(EmployeeFilter filter) {
        return new JPAQuery<EmployeeEntity>(entityManager)
                .select(QEmployeeEntity.employeeEntity)
                .from(QEmployeeEntity.employeeEntity)
                .where(QEmployeeEntity.employeeEntity.firstName.containsIgnoreCase("tim"))
                .fetch();
    }
}
