package org.openmrs.module.datamigration.util.Model;

import org.codehaus.jackson.annotate.*;
import org.openmrs.BaseOpenmrsMetadata;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({ "identifier", "identifierType", "locationId", "preferred" })
public class Identifier extends BaseOpenmrsMetadata {
	
	@JsonProperty("identifier")
	private String identifier;
	
	@JsonProperty("identifierType")
	private Integer identifierType;
	
	@JsonProperty("locationId")
	private Integer locationId;
	
	@JsonProperty("preferred")
	private Boolean preferred;
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public Integer getIdentifierType() {
		return identifierType;
	}
	
	public void setIdentifierType(Integer identifierType) {
		this.identifierType = identifierType;
	}
	
	public Integer getLocationId() {
		return locationId;
	}
	
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	public Boolean getPreferred() {
		return preferred;
	}
	
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
