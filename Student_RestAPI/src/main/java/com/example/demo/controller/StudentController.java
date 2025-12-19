package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.StudentInfo;
import com.example.demo.repository.StudentRepo;

@RestController
public class StudentController {
	@Autowired
	StudentRepo stdrep;
	@PostMapping("/api/students")
	public ResponseEntity<StudentInfo> saveStudent(@RequestBody StudentInfo studentinfo)
	{
		return new ResponseEntity<>(stdrep.save(studentinfo),HttpStatus.CREATED);
	}
	@GetMapping("/api/students/{id}")
	public ResponseEntity<StudentInfo> getStudent(@PathVariable long id)
	{
		Optional<StudentInfo> std=stdrep.findById(id);
		if(std.isPresent())
		{
			return new ResponseEntity<>(std.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping("/api/students")
	public ResponseEntity<List<StudentInfo>> getStudents()
	{
		return new ResponseEntity<>(stdrep.findAll(),HttpStatus.OK);
	}
	@PutMapping("/api/students/{id}")
	public ResponseEntity<StudentInfo> update(@PathVariable long id, @RequestBody StudentInfo info)
	{
		Optional<StudentInfo> std=stdrep.findById(id);
		if(std.isPresent())
		{
			std.get().setStudentName(info.getStudentName());
			std.get().setStudentEmail(info.getStudentEmail());
			std.get().setStudentAddress(info.getStudentAddress());
			return new ResponseEntity<>(stdrep.save(std.get()),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<StudentInfo> deletestudent(@PathVariable long id)
	{
		Optional<StudentInfo> std=stdrep.findById(id);
		if(std.isPresent())
		{
			stdrep.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
