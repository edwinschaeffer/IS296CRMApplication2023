package crm.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POTENTIAL_LEADS")
public class PotentialLead {
	@Id
	@Column(nullable = false, name = "ID")
	private String id; 
	@Column(nullable = true, name = "AGE_OF_BUSINESS")
	private String ageOfBusiness;
	private String city;
	private String company;
	private String country;
	private String area;
	@Column(nullable = true, name = "EMPLOYEE_COUNT")
	private String employeeCount;
	private String industry;
	private String phone;
	@Column(nullable = true, name = "POTENTIAL_LEAD_LOCATION__LATITUDE__S")
	private String potentialLeadLocationLatitude;
	@Column(nullable = true, name = "POTENTIAL_LEAD_LOCATION__LONGITUDE__S")
	private String potentialLeadLocationLongitude;
	private String sector;
	private String status;
	private String street;
	private String website;
	@Column(nullable = true, name = "ZIP_CODE")
	private String zipCode;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgeOfBusiness() {
		return ageOfBusiness;
	}
	public void setAgeOfBusiness(String ageOfBusiness) {
		this.ageOfBusiness = ageOfBusiness;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPotentialLeadLocationLatitude() {
		return potentialLeadLocationLatitude;
	}
	public void setPotentialLeadLocationLatitude(String potentialLeadLocationLatitude) {
		this.potentialLeadLocationLatitude = potentialLeadLocationLatitude;
	}
	public String getPotentialLeadLocationLongitude() {
		return potentialLeadLocationLongitude;
	}
	public void setPotentialLeadLocationLongitude(String potentialLeadLocationLongitude) {
		this.potentialLeadLocationLongitude = potentialLeadLocationLongitude;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
