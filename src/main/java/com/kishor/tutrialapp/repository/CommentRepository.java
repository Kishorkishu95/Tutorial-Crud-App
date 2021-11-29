package com.kishor.tutrialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.tutrialapp.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByTutorialId(Long tutorialId);
}
