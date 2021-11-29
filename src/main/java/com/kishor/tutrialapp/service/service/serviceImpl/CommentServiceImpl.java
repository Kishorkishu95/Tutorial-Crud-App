/**
 * 
 */
package com.kishor.tutrialapp.service.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kishor.tutrialapp.Dto.CommentDto;
import com.kishor.tutrialapp.entity.Comment;
import com.kishor.tutrialapp.entity.Tutorial;
import com.kishor.tutrialapp.exception.BlogApiException;
import com.kishor.tutrialapp.exception.TutorialNotFoundException;
import com.kishor.tutrialapp.repository.CommentRepository;
import com.kishor.tutrialapp.repository.TutorialRepository;
import com.kishor.tutrialapp.service.service.CommentService;

/**
 * @author Kishu
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private TutorialRepository tutorialRepository;
	
	private ModelMapper modelMapper;

	public CommentServiceImpl(TutorialRepository tutorialRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
		this.tutorialRepository = tutorialRepository;
		this.commentRepository = commentRepository;
		this.modelMapper=modelMapper;
	}

	private CommentDto mapToDto(Comment comment) {
		CommentDto dtoComment = modelMapper.map(comment, CommentDto.class);

		return dtoComment;
	}

	private Comment mapToEntity(CommentDto dto) {
		Comment comment = modelMapper.map(dto, Comment.class);

		return comment;
	}

	@Override
	public CommentDto createComment(Long tutorialId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		Tutorial getOne = tutorialRepository.findById(tutorialId)
				.orElseThrow(() -> new TutorialNotFoundException("Tutorial", "Id", tutorialId));
		comment.setTutorial(getOne);
		Comment newComment = commentRepository.save(comment);
		CommentDto dtoComment = mapToDto(newComment);

		return dtoComment;
	}

	@Override
	public List<CommentDto> getAllCommentsOfTutorial(Long tutorialId) {
		// TODO Auto-generated method stub
		List<Comment> commentsForTutorial = commentRepository.findByTutorialId(tutorialId);

		return commentsForTutorial.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long tutorialId, Long commentId) {
		Comment comment = getPostCommentById(tutorialId, commentId);
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateCommentOfTutorial(Long tutorialId, Long commentId, CommentDto commentRequestDto) {
		Comment comment = getPostCommentById(tutorialId, commentId);
		comment.setName(commentRequestDto.getName());
		comment.setEmail(commentRequestDto.getEmail());
		comment.setBody(commentRequestDto.getBody());
		Comment updatedComment = commentRepository.save(comment);
		return mapToDto(updatedComment);
	}

	@Override
	public void deleteCommentForTheTutorial(Long tutorialId, Long commentId) {
		// TODO Auto-generated method stub
		Comment comment = getPostCommentById(tutorialId, commentId);
		commentRepository.delete(comment);
		
	}

	/**
	 * @param tutorialId
	 * @param commentId
	 * @return
	 */
	private Comment getPostCommentById(Long tutorialId, Long commentId) {
		Tutorial getOne =tutorialRepository.findById(tutorialId)
				.orElseThrow(() -> new TutorialNotFoundException("Tutorial", "Id", tutorialId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new TutorialNotFoundException("Comment", "id", commentId));
		if(!comment.getTutorial().getId().equals(getOne.getId())){
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to a particular tutorial");
		}
		return comment;
	}

}
