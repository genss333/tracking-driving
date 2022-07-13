package misl.spring.model;

public class ProviderModel{

	private int providerId; 
	private String name; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private double latitude; 
	private double longitude; 
	private String createdAt; 
	private String updatedAt; 
	private int providerStatus; 
	private String email; 
	private String phone; 

	@Override
	public String toString() {

		return "ProviderModel [providerId=" + providerId + ", name=" + name + ", address=" + address + ", " + 
 			   "tambon=" + tambon + ", amphur=" + amphur + ", province=" + province + ", " + 
 			   "latitude=" + latitude + ", longitude=" + longitude + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + ", providerStatus=" + providerStatus + ", email=" + email + ", " + 
 			   "phone=" + phone + "]";
	} 

	public int getProviderId(){ 
		return providerId; 
	} 
	public void setProviderId(int providerId){ 
		 this.providerId = providerId;
	} 

	public String getName(){ 
		return name; 
	} 
	public void setName(String name){ 
		 this.name = name;
	} 

	public String getAddress(){ 
		return address; 
	} 
	public void setAddress(String address){ 
		 this.address = address;
	} 

	public String getTambon(){ 
		return tambon; 
	} 
	public void setTambon(String tambon){ 
		 this.tambon = tambon;
	} 

	public String getAmphur(){ 
		return amphur; 
	} 
	public void setAmphur(String amphur){ 
		 this.amphur = amphur;
	} 

	public String getProvince(){ 
		return province; 
	} 
	public void setProvince(String province){ 
		 this.province = province;
	} 

	public double getLatitude(){ 
		return latitude; 
	} 
	public void setLatitude(double latitude){ 
		 this.latitude = latitude;
	} 

	public double getLongitude(){ 
		return longitude; 
	} 
	public void setLongitude(double longitude){ 
		 this.longitude = longitude;
	} 

	public String getCreatedAt(){ 
		return createdAt; 
	} 
	public void setCreatedAt(String createdAt){ 
		 this.createdAt = createdAt;
	} 

	public String getUpdatedAt(){ 
		return updatedAt; 
	} 
	public void setUpdatedAt(String updatedAt){ 
		 this.updatedAt = updatedAt;
	} 

	public int getProviderStatus(){ 
		return providerStatus; 
	} 
	public void setProviderStatus(int providerStatus){ 
		 this.providerStatus = providerStatus;
	} 

	public String getEmail(){ 
		return email; 
	} 
	public void setEmail(String email){ 
		 this.email = email;
	} 

	public String getPhone(){ 
		return phone; 
	} 
	public void setPhone(String phone){ 
		 this.phone = phone;
	} 

}