package com.kishor.tutrialapp.service.service;

import java.util.List;

import com.kishor.tutrialapp.Dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long tutorialId, CommentDto commentDto);
	List<CommentDto> getAllCommentsOfTutorial(Long postId);
	CommentDto getCommentById(Long tutorialId, Long commentId);
	CommentDto updateCommentOfTutorial(Long tutorialId,Long commentId,CommentDto commentRequestDto);
	void deleteCommentForTheTutorial(Long tutorialId, Long commentId);
}
