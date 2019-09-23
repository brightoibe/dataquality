package org.openmrs.module.datamigration.util.Model;

import org.openmrs.BaseOpenmrsMetadata;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Migration extends BaseOpenmrsMetadata {
	
	private String prefix;
	
	private String givenName;
	
	private String middleName;
	
	private String surname;
	
	private String creator;
	
	private Date dateCreated;
	
	private String birthDate;
	
	private String birthdateEstimated;
	
	private String dead;
	
	private String deathDate;
	
	private String causeOfDeath;
	
	private String gender;
	
	private String familyName;
	
	private Facility facility;

	Set<Encounter> encounters = new LinkedHashSet<>();
	
	private Address address;

	private String phone;

	Set<Identifier> identifiers = new LinkedHashSet<>();


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(Set<Encounter> encounters) {
		this.encounters = encounters;
	}

	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<Identifier> identifiers) {
		this.identifiers = identifiers;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getBirthdateEstimated() {
		return birthdateEstimated;
	}
	
	public void setBirthdateEstimated(String birthdateEstimated) {
		this.birthdateEstimated = birthdateEstimated;
	}
	
	public String getDead() {
		return dead;
	}
	
	public void setDead(String dead) {
		this.dead = dead;
	}
	
	public String getDeathDate() {
		return deathDate;
	}
	
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	
	public Facility getFacility() {
		return facility;
	}
	
	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
