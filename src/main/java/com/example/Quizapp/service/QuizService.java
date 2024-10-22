package com.example.Quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Quizapp.Repository.QuestionRepo;
import com.example.Quizapp.Repository.QuizRepo;
import com.example.Quizapp.model.Question;
import com.example.Quizapp.model.QuestionWrapper;
import com.example.Quizapp.model.Quiz;
import com.example.Quizapp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionRepo questionRepo;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questionRepo.findRandomQuestionByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepo.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		Optional<Quiz> questions = quizRepo.findById(id);
		List<Question> questionFromDB = questions.get().getQuestions();
		
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		
		for(Question q: questionFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Quiz quiz = quizRepo.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		
		int right=0;
		int i=0;
		
		for(Response r: responses) {
			if(r.getResponse().equals(questions.get(i).getRightAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
}
