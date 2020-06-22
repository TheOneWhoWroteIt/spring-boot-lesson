package com.nevermind.springboot.lesson.repository;

import com.nevermind.springboot.lesson.dto.EmployeeFilter;
import com.nevermind.springboot.lesson.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeCustomRepository {

    List<EmployeeEntity> findByFilter(EmployeeFilter filter);
}
