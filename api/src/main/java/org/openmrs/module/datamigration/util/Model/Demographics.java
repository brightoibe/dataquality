package org.openmrs.module.datamigration.util.Model;

import org.codehaus.jackson.annotate.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({ "preferred", "prefix", "given_name", "middle_name", "family_name_prefix", "family_name",
        "family_name2", "family_name_suffix", "creator", "date_created", "phone", "gender", "birthdate",
        "birthdate_estimated", "dead", "death_date", "cause_of_death", "address", "identifiers", "location" })
public class Demographics {
	
	@JsonProperty("preferred")
	private String preferred;
	
	@JsonProperty("prefix")
	private String prefix;
	
	@JsonProperty("given_name")
	private String givenName;
	
	@JsonProperty("middle_name")
	private String middleName;
	
	@JsonProperty("family_name_prefix")
	private String familyNamePrefix;
	
	@JsonProperty("family_name")
	private String familyName;
	
	@JsonProperty("family_name2")
	private String familyName2;
	
	@JsonProperty("family_name_suffix")
	private String familyNameSuffix;
	
	@JsonProperty("creator")
	private String creator;
	
	@JsonProperty("date_created")
	private String dateCreated;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("birthdate")
	private String birthdate;
	
	@JsonProperty("birthdate_estimated")
	private String birthdateEstimated;
	
	@JsonProperty("dead")
	private String dead;
	
	@JsonProperty("death_date")
	private String deathDate;
	
	@JsonProperty("cause_of_death")
	private String causeOfDeath;
	
	@JsonProperty("address")
	private Address address;
	
	@JsonProperty("identifiers")
	private List<Identifier> identifiers = null;
	
	@JsonProperty("location")
	private Location location;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	/**
	 * No args constructor for use in serialization
	 */
	public Demographics() {
	}
	
	/**
	 * @param middleName
	 * @param familyName2
	 * @param phone
	 * @param birthdateEstimated
	 * @param location
	 * @param identifiers
	 * @param familyName
	 * @param givenName
	 * @param deathDate
	 * @param preferred
	 * @param creator
	 * @param familyNameSuffix
	 * @param address
	 * @param prefix
	 * @param birthdate
	 * @param causeOfDeath
	 * @param dateCreated
	 * @param gender
	 * @param dead
	 * @param familyNamePrefix
	 */
	public Demographics(String preferred, String prefix, String givenName, String middleName, String familyNamePrefix,
	    String familyName, String familyName2, String familyNameSuffix, String creator, String dateCreated, String phone,
	    String gender, String birthdate, String birthdateEstimated, String dead, String deathDate, String causeOfDeath,
	    Address address, List<Identifier> identifiers, Location location) {
		super();
		this.preferred = preferred;
		this.prefix = prefix;
		this.givenName = givenName;
		this.middleName = middleName;
		this.familyNamePrefix = familyNamePrefix;
		this.familyName = familyName;
		this.familyName2 = familyName2;
		this.familyNameSuffix = familyNameSuffix;
		this.creator = creator;
		this.dateCreated = dateCreated;
		this.phone = phone;
		this.gender = gender;
		this.birthdate = birthdate;
		this.birthdateEstimated = birthdateEstimated;
		this.dead = dead;
		this.deathDate = deathDate;
		this.causeOfDeath = causeOfDeath;
		this.address = address;
		this.identifiers = identifiers;
		this.location = location;
	}
	
	@JsonProperty("preferred")
	public String getPreferred() {
		return preferred;
	}
	
	@JsonProperty("preferred")
	public void setPreferred(String preferred) {
		this.preferred = preferred;
	}
	
	@JsonProperty("prefix")
	public String getPrefix() {
		return prefix;
	}
	
	@JsonProperty("prefix")
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@JsonProperty("given_name")
	public String getGivenName() {
		return givenName;
	}
	
	@JsonProperty("given_name")
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	@JsonProperty("middle_name")
	public String getMiddleName() {
		return middleName;
	}
	
	@JsonProperty("middle_name")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@JsonProperty("family_name_prefix")
	public String getFamilyNamePrefix() {
		return familyNamePrefix;
	}
	
	@JsonProperty("family_name_prefix")
	public void setFamilyNamePrefix(String familyNamePrefix) {
		this.familyNamePrefix = familyNamePrefix;
	}
	
	@JsonProperty("family_name")
	public String getFamilyName() {
		return familyName;
	}
	
	@JsonProperty("family_name")
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	@JsonProperty("family_name2")
	public String getFamilyName2() {
		return familyName2;
	}
	
	@JsonProperty("family_name2")
	public void setFamilyName2(String familyName2) {
		this.familyName2 = familyName2;
	}
	
	@JsonProperty("family_name_suffix")
	public String getFamilyNameSuffix() {
		return familyNameSuffix;
	}
	
	@JsonProperty("family_name_suffix")
	public void setFamilyNameSuffix(String familyNameSuffix) {
		this.familyNameSuffix = familyNameSuffix;
	}
	
	@JsonProperty("creator")
	public String getCreator() {
		return creator;
	}
	
	@JsonProperty("creator")
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@JsonProperty("date_created")
	public String getDateCreated() {
		return dateCreated;
	}
	
	@JsonProperty("date_created")
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}
	
	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}
	
	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonProperty("birthdate")
	public String getBirthdate() {
		return birthdate;
	}
	
	@JsonProperty("birthdate")
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	@JsonProperty("birthdate_estimated")
	public String getBirthdateEstimated() {
		return birthdateEstimated;
	}
	
	@JsonProperty("birthdate_estimated")
	public void setBirthdateEstimated(String birthdateEstimated) {
		this.birthdateEstimated = birthdateEstimated;
	}
	
	@JsonProperty("dead")
	public String getDead() {
		return dead;
	}
	
	@JsonProperty("dead")
	public void setDead(String dead) {
		this.dead = dead;
	}
	
	@JsonProperty("death_date")
	public String getDeathDate() {
		return deathDate;
	}
	
	@JsonProperty("death_date")
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	
	@JsonProperty("cause_of_death")
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	
	@JsonProperty("cause_of_death")
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	
	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}
	
	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@JsonProperty("identifiers")
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}
	
	@JsonProperty("identifiers")
	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}
	
	@JsonProperty("location")
	public Location getLocation() {
		return location;
	}
	
	@JsonProperty("location")
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
}
