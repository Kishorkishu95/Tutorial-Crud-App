package com.kishor.tutrialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.tutrialapp.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
 List<Tutorial> findByPublished(boolean published);
 List<Tutorial> findByTitle(String title);
}
