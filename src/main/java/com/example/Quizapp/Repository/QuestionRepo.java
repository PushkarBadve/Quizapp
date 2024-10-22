package com.example.Quizapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Quizapp.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	
	@Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);
}
