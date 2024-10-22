package com.example.Quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Quizapp.Repository.QuestionRepo;
import com.example.Quizapp.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo qr;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(qr.findAll(),HttpStatus.OK);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(qr.findByCategory(category),HttpStatus.OK);			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			qr.save(question);
			return new ResponseEntity<>("Success",HttpStatus.CREATED); 			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Enter Values in right manner",HttpStatus.BAD_REQUEST);
	}
	
}
