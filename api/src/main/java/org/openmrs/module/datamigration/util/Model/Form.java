package org.openmrs.module.datamigration.util.Model;

import org.codehaus.jackson.annotate.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({ "formTypeId", "locationId", "dateCreated", "obs" })
public class Form {
	
	@JsonProperty("formTypeId")
	private String formTypeId;
	
	@JsonProperty("locationId")
	private String locationId;
	
	@JsonProperty("dateCreated")
	private String dateCreated;
	
	@JsonProperty("obs")
	private List<Obs> obs = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	/**
	 * No args constructor for use in serialization
	 */
	public Form() {
	}
	
	/**
	 * @param obs
	 * @param formTypeId
	 * @param locationId
	 * @param dateCreated
	 */
	public Form(String formTypeId, String locationId, String dateCreated, List<Obs> obs) {
		super();
		this.formTypeId = formTypeId;
		this.locationId = locationId;
		this.dateCreated = dateCreated;
		this.obs = obs;
	}
	
	@JsonProperty("formTypeId")
	public String getFormTypeId() {
		return formTypeId;
	}
	
	@JsonProperty("formTypeId")
	public void setFormTypeId(String formTypeId) {
		this.formTypeId = formTypeId;
	}
	
	@JsonProperty("locationId")
	public String getLocationId() {
		return locationId;
	}
	
	@JsonProperty("locationId")
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	@JsonProperty("dateCreated")
	public String getDateCreated() {
		return dateCreated;
	}
	
	@JsonProperty("dateCreated")
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@JsonProperty("obs")
	public List<Obs> getObs() {
		return obs;
	}
	
	@JsonProperty("obs")
	public void setObs(List<Obs> obs) {
		this.obs = obs;
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
