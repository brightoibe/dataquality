package org.openmrs.module.datamigration.util.Model;

import org.codehaus.jackson.annotate.*;
import org.openmrs.BaseOpenmrsMetadata;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({ "country", "latitude", "longitude", "address1", "address2", "address3", "city_village",
        "state_province", "postal_code" })
public class Address extends BaseOpenmrsMetadata {
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonProperty("address1")
	private String address1;
	
	@JsonProperty("address2")
	private String address2;
	
	@JsonProperty("address3")
	private String address3;
	
	@JsonProperty("city_village")
	private String cityVillage;
	
	@JsonProperty("state_province")
	private String stateProvince;
	
	@JsonProperty("postal_code")
	private String postalCode;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	/**
	 * No args constructor for use in serialization
	 */
	public Address() {
	}
	
	/**
	 * @param postalCode
	 * @param stateProvince
	 * @param cityVillage
	 * @param address1
	 * @param address2
	 * @param longitude
	 * @param address3
	 * @param latitude
	 * @param country
	 */
	public Address(String country, String latitude, String longitude, String address1, String address2, String address3,
	    String cityVillage, String stateProvince, String postalCode) {
		super();
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.cityVillage = cityVillage;
		this.stateProvince = stateProvince;
		this.postalCode = postalCode;
	}
	
	@JsonProperty("country")
	public String getCountry() {
		return country;
	}
	
	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}
	
	@JsonProperty("latitude")
	public String getLatitude() {
		return latitude;
	}
	
	@JsonProperty("latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@JsonProperty("longitude")
	public String getLongitude() {
		return longitude;
	}
	
	@JsonProperty("longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@JsonProperty("address1")
	public String getAddress1() {
		return address1;
	}
	
	@JsonProperty("address1")
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@JsonProperty("address2")
	public String getAddress2() {
		return address2;
	}
	
	@JsonProperty("address2")
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@JsonProperty("address3")
	public String getAddress3() {
		return address3;
	}
	
	@JsonProperty("address3")
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	@JsonProperty("city_village")
	public String getCityVillage() {
		return cityVillage;
	}
	
	@JsonProperty("city_village")
	public void setCityVillage(String cityVillage) {
		this.cityVillage = cityVillage;
	}
	
	@JsonProperty("state_province")
	public String getStateProvince() {
		return stateProvince;
	}
	
	@JsonProperty("state_province")
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	
	@JsonProperty("postal_code")
	public String getPostalCode() {
		return postalCode;
	}
	
	@JsonProperty("postal_code")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
