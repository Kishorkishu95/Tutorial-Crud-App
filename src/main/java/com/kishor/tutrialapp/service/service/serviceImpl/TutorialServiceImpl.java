package com.kishor.tutrialapp.service.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.tutrialapp.Dto.TutorialDto;
import com.kishor.tutrialapp.entity.Tutorial;
import com.kishor.tutrialapp.exception.TutorialNotFoundException;
import com.kishor.tutrialapp.repository.TutorialRepository;
import com.kishor.tutrialapp.service.service.TutorialService;

@Service
public class TutorialServiceImpl implements TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private TutorialDto mapToDto(Tutorial tutorial) {
		TutorialDto dto= modelMapper.map(tutorial, TutorialDto.class);
		return dto;
	}
	
	private Tutorial mapToEntity(TutorialDto tutorialDto) {
		Tutorial tutorial=modelMapper.map(tutorialDto, Tutorial.class);
		return tutorial;
	}

	@Override
	public TutorialDto createNewTutorial(TutorialDto tutorialDto) {
		Tutorial newTutorial = mapToEntity(tutorialDto);
		tutorialRepository.save(newTutorial);
		TutorialDto tutorialDto2=mapToDto(newTutorial);
		return tutorialDto2;
	}

	@Override
	public TutorialDto getTutorialById(Long id) {
		Tutorial tutorial=tutorialRepository.findById(id).orElseThrow(()->new TutorialNotFoundException("Tutorial","id",id));
		return mapToDto(tutorial);
	}

	@Override
	public List<TutorialDto> getAllTutorials() {
		List<Tutorial> allTutorials = tutorialRepository.findAll();
		List<TutorialDto> allTutos = allTutorials.stream().map(tutorial->mapToDto(tutorial)).collect(Collectors.toList());
		return allTutos;
	}

	@Override
	public TutorialDto updateTutorial(Long id, TutorialDto tutorialDto) {
		Tutorial tutorial=tutorialRepository.findById(id).orElseThrow(()->new TutorialNotFoundException("Tutorial","id",id));
		if(tutorial!=null && tutorial.getId().equals(id)) {
			tutorial.setTitle(tutorialDto.getTitle());
			tutorial.setDescription(tutorialDto.getDescription());
			tutorial.setPublished(tutorialDto.isPublished());
		}
		else {
			throw new TutorialNotFoundException("Tutorial", "Id", id);
		}
		tutorialRepository.save(tutorial);
		return mapToDto(tutorial);
	}

	@Override
	public void deleteTutorial(Long id) {
		Tutorial tutorial=tutorialRepository.findById(id).orElseThrow(()->new TutorialNotFoundException("Tutorial","id",id));
		tutorialRepository.delete(tutorial);
		
	}

}
