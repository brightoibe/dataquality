package org.openmrs.module.datamigration.util.Model;

import org.codehaus.jackson.annotate.*;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({ "address", "lga", "facility", "facillityLga", "datim" })
public class Location {
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("lga")
	private String lga;
	
	@JsonProperty("facility")
	private String facility;
	
	@JsonProperty("facillityLga")
	private String facillityLga;
	
	@JsonProperty("datim")
	private String datim;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	/**
	 * No args constructor for use in serialization
	 */
	public Location() {
	}
	
	/**
	 * @param facillityLga
	 * @param facility
	 * @param address
	 * @param datim
	 * @param lga
	 */
	public Location(String address, String lga, String facility, String facillityLga, String datim) {
		super();
		this.address = address;
		this.lga = lga;
		this.facility = facility;
		this.facillityLga = facillityLga;
		this.datim = datim;
	}
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}
	
	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonProperty("lga")
	public String getLga() {
		return lga;
	}
	
	@JsonProperty("lga")
	public void setLga(String lga) {
		this.lga = lga;
	}
	
	@JsonProperty("facility")
	public String getFacility() {
		return facility;
	}
	
	@JsonProperty("facility")
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	@JsonProperty("facillityLga")
	public String getFacillityLga() {
		return facillityLga;
	}
	
	@JsonProperty("facillityLga")
	public void setFacillityLga(String facillityLga) {
		this.facillityLga = facillityLga;
	}
	
	@JsonProperty("datim")
	public String getDatim() {
		return datim;
	}
	
	@JsonProperty("datim")
	public void setDatim(String datim) {
		this.datim = datim;
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
