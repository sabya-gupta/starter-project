package com.maybank.labs.starter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity
@Table(name="REVINFO")
public class RevInfoEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "starter-seq")
	@SequenceGenerator(name = "starter-seq", sequenceName = "STARTER_SEQ", allocationSize = 1)
    @RevisionNumber
    @Column(name="REV")
    private long rev;

    @RevisionTimestamp
    @Column(name="REVTSTMP")
    private long revtstmp;

	public long getRev() {
		return rev;
	}

	public void setRev(long rev) {
		this.rev = rev;
	}

	public long getRevtstmp() {
		return revtstmp;
	}

	public void setRevtstmp(long revtstmp) {
		this.revtstmp = revtstmp;
	}

}
