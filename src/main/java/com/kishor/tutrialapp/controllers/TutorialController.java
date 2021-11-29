package com.kishor.tutrialapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.tutrialapp.Dto.TutorialDto;
import com.kishor.tutrialapp.service.service.TutorialService;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

	@Autowired
	private TutorialService tutorialService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TutorialDto> createTutorial(@RequestBody TutorialDto dto) {
		TutorialDto tutorialDto=tutorialService.createNewTutorial(dto);
		return new ResponseEntity<>(tutorialDto,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TutorialDto> getOneTutorial(@PathVariable("id") Long id) {
		TutorialDto tutorialDto=tutorialService.getTutorialById(id);
		return new ResponseEntity<>(tutorialDto,HttpStatus.FOUND);
		
	}
	
	@GetMapping
	public List<TutorialDto> getAllTutorials() {
		return tutorialService.getAllTutorials();
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<TutorialDto> updateTutorial(@PathVariable("id") Long id,@RequestBody TutorialDto dto){
		return new ResponseEntity<>(tutorialService.updateTutorial(id, dto),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTutorial(@PathVariable("id") Long id){
		tutorialService.deleteTutorial(id);
		return new ResponseEntity<String>("Tutorial has been deleted successfully.",HttpStatus.OK);
	}
	
}
