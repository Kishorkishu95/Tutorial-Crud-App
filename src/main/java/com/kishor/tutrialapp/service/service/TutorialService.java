package com.kishor.tutrialapp.service.service;

import java.util.List;

import com.kishor.tutrialapp.Dto.TutorialDto;

public interface TutorialService {

	TutorialDto createNewTutorial(TutorialDto tutorialDto);
	TutorialDto getTutorialById(Long id);
	List<TutorialDto> getAllTutorials();
	TutorialDto updateTutorial(Long id,TutorialDto tutorialDto);
	void deleteTutorial(Long id);
}
