package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.StudentInfo;

public interface StudentRepo extends JpaRepository<StudentInfo,Long>{

}
