package com.thinktalentindia.CrudeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinktalentindia.CrudeProject.model.StudentData;

public interface StudentRepository extends JpaRepository<StudentData, Long> {

}