package com.maybank.labs.starter.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maybank.labs.starter.entities.StarterPerson;
import com.maybank.labs.starter.exception.StarterCustomException;
import com.maybank.labs.starter.repository.StarterPersonRepository;
import com.maybank.labs.starter.utils.StarterConstants;

@Service
public class StarterPersonService {

	StarterPersonRepository starterPersonRepository;

	public StarterPersonService(StarterPersonRepository starterPersonRepository) {
		this.starterPersonRepository = starterPersonRepository;
	}

	public List<StarterPerson> getByName(String name) {
		return starterPersonRepository.getByName(name);
	}

	public List<StarterPerson> getBySurName(String surName) {
		return starterPersonRepository.getBySurName(surName);
	}

	
	public List<StarterPerson> findAll() {
		return starterPersonRepository.findAll();
	}

	public StarterPerson getPersonById(Integer id) throws StarterCustomException{
		StarterPerson sp = starterPersonRepository.getPersonById(id);
		if (sp == null) {
			throw new StarterCustomException(
					HttpStatus.BAD_REQUEST, 
					999, 
					LocalDateTime.now(), 
					"Starter Person Does Not exists");
		}
		return sp;
	}

	public StarterPerson save(StarterPerson person) {
		return starterPersonRepository.save(person);
	}

	public long delete(int id) {
		return starterPersonRepository.deleteById(id);
	}

}
