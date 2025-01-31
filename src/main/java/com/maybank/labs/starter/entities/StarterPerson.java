package com.maybank.labs.starter.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited // if you want to audit with using Envers
@Table(name = "STARTER_PERSON")
public class StarterPerson implements Serializable {

	private static final long serialVersionUID = 1894957097727172217L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "starter-seq")
	@SequenceGenerator(name = "starter-seq", sequenceName = "STARTER_SEQ", allocationSize = 1)
	@Column(name = "P_ID")
	private Integer id;

	@Column(name = "P_NAME")
	private String name;

	@Column(name = "P_SN")
	private String surName;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return surName;
	}

	public void setSurname(final String surname) {
		this.surName = surname;
	}

}