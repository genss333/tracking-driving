package misl.spring.model;

public class MarketModel{

	private int marketId; 
	private String name; 
	private double latitude; 
	private double longitude; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private String phoneNumber; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "MarketModel [marketId=" + marketId + ", name=" + name + ", latitude=" + latitude + ", " + 
 			   "longitude=" + longitude + ", address=" + address + ", tambon=" + tambon + ", " + 
 			   "amphur=" + amphur + ", province=" + province + ", phoneNumber=" + phoneNumber + ", " + 
 			   "createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

	public int getMarketId(){ 
		return marketId; 
	} 
	public void setMarketId(int marketId){ 
		 this.marketId = marketId;
	} 

	public String getName(){ 
		return name; 
	} 
	public void setName(String name){ 
		 this.name = name;
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

	public String getPhoneNumber(){ 
		return phoneNumber; 
	} 
	public void setPhoneNumber(String phoneNumber){ 
		 this.phoneNumber = phoneNumber;
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

}