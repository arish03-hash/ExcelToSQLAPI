package com.api.exceltosql.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.exceltosql.helper.ExcelHelper;
import com.api.exceltosql.model.Students;
import com.api.exceltosql.service.StudentService;

@RestController
@CrossOrigin("*")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
		if(ExcelHelper.checkExcelFormat(file)) {
			this.studentService.save(file);
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved to DB"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel file only");
	}
	
	@GetMapping("/student")
	public List<Students> getAllProduct(){
		return this.studentService.getAllStudents();
	}
}
