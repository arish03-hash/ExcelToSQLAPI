package com.api.exceltosql.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.exceltosql.helper.ExcelHelper;
import com.api.exceltosql.model.Students;
import com.api.exceltosql.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	public void save(MultipartFile multipartFile) {
		try {
			List<Students> students = ExcelHelper.convertExcelToListOfStudents(multipartFile.getInputStream());
			this.studentRepo.saveAll(students);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Students> getAllStudents(){
		return this.studentRepo.findAll();
	}
}
