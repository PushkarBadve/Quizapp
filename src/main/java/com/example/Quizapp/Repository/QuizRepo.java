package com.example.Quizapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Quizapp.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
