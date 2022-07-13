package misl.spring.model;

public class SellerModel{

	private int sellerId; 
	private MarketModel marketId; 
	private String username; 
	private String password; 
	private String firstname; 
	private String lastname; 
	private String address; 
	private String phoneNumber; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "SellerModel [sellerId=" + sellerId + ", marketId=" + marketId + ", username=" + username + ", " + 
 			   "password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", " + 
 			   "address=" + address + ", phoneNumber=" + phoneNumber + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getSellerId(){ 
		return sellerId; 
	} 
	public void setSellerId(int sellerId){ 
		 this.sellerId = sellerId;
	} 

	public MarketModel getMarketId(){ 
		return marketId; 
	} 
	public void setMarketId(MarketModel marketId){ 
		 this.marketId = marketId;
	} 

	public String getUsername(){ 
		return username; 
	} 
	public void setUsername(String username){ 
		 this.username = username;
	} 

	public String getPassword(){ 
		return password; 
	} 
	public void setPassword(String password){ 
		 this.password = password;
	} 

	public String getFirstname(){ 
		return firstname; 
	} 
	public void setFirstname(String firstname){ 
		 this.firstname = firstname;
	} 

	public String getLastname(){ 
		return lastname; 
	} 
	public void setLastname(String lastname){ 
		 this.lastname = lastname;
	} 

	public String getAddress(){ 
		return address; 
	} 
	public void setAddress(String address){ 
		 this.address = address;
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