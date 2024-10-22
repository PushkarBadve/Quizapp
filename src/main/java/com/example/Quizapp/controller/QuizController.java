package com.example.Quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Quizapp.model.QuestionWrapper;
import com.example.Quizapp.model.Response;
import com.example.Quizapp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService qs;
	
	@PostMapping("/create")
	ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		return qs.createQuiz(category, numQ, title);
	}
	
	@GetMapping("/get/{id}")
	ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		return qs.getQuiz(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
		return qs.calculateResult(id,responses);
	}
}
