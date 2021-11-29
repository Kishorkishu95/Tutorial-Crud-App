package com.kishor.tutrialapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.tutrialapp.Dto.CommentDto;
import com.kishor.tutrialapp.service.service.CommentService;


@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/tutorials/{tutorialId}/comments")
	public ResponseEntity<CommentDto> createCommentForTutorial(@PathVariable(value = "tutorialId") Long tutorialId,
			@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(tutorialId, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("/tutorials/{tutorialId}/comments")
	public List<CommentDto> getAllCommentsForTheTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
		return commentService.getAllCommentsOfTutorial(tutorialId);

	}

	@GetMapping("/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentForTutorialById(@PathVariable(value = "tutorialId") Long tutorialId,
			@PathVariable(value = "commentId") Long commentId) {
		CommentDto commentFortutorial = commentService.getCommentById(tutorialId, commentId);
		return new ResponseEntity<>(commentFortutorial, HttpStatus.OK);
	}

	@PutMapping("/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateCommentOfTheTutorial(@PathVariable(value = "tutorialId") Long tutorialId,
			@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody CommentDto commentReqDto) {
		CommentDto updateComment = commentService.updateCommentOfTutorial(tutorialId, commentId, commentReqDto);
		return new ResponseEntity<>(updateComment, HttpStatus.OK);

	}

	@DeleteMapping("/tutorials/{tutorialId}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentOfTheTutorial(@PathVariable(value = "tutorialId") Long tutorialId,
			@PathVariable(value = "commentId") Long commentId) {
		commentService.deleteCommentForTheTutorial(tutorialId, commentId);
		return new ResponseEntity<>("Comment of the tutorial has been deleted.", HttpStatus.OK);

	}
}
