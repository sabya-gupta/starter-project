package com.maybank.labs.starter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maybank.labs.starter.entities.StarterPerson;

@Repository
public interface StarterPersonRepository extends JpaRepository<StarterPerson, Integer> {
	public List<StarterPerson> getByName(String name);
	public List<StarterPerson> getBySurName(String surName);

	@Query("SELECT P FROM StarterPerson P WHERE P.id = :id")
	public StarterPerson getPersonById(Integer id);

	public StarterPerson save(StarterPerson person);
	public long deleteById(int id);
}