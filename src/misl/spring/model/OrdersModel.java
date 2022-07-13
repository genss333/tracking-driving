package misl.spring.model;

public class OrdersModel{

	private int orderId; 
	private SellerModel sellerId; 
	private String orderNumber; 
	private String phoneNumber; 
	private String firstname; 
	private String lastname; 
	private String recipientPhoneNumber; 
	private String recipientLastname; 
	private String recipientFirstname; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private String zipcode; 
	private double latitude; 
	private double longitude; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "OrdersModel [orderId=" + orderId + ", sellerId=" + sellerId + ", orderNumber=" + orderNumber + ", " + 
 			   "phoneNumber=" + phoneNumber + ", firstname=" + firstname + ", lastname=" + lastname + ", " + 
 			   "recipientPhoneNumber=" + recipientPhoneNumber + ", recipientLastname=" + recipientLastname + ", recipientFirstname=" + recipientFirstname + ", " + 
 			   "address=" + address + ", tambon=" + tambon + ", amphur=" + amphur + ", " + 
 			   "province=" + province + ", zipcode=" + zipcode + ", latitude=" + latitude + ", " + 
 			   "longitude=" + longitude + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

	public int getOrderId(){ 
		return orderId; 
	} 
	public void setOrderId(int orderId){ 
		 this.orderId = orderId;
	} 

	public SellerModel getSellerId(){ 
		return sellerId; 
	} 
	public void setSellerId(SellerModel sellerId){ 
		 this.sellerId = sellerId;
	} 

	public String getOrderNumber(){ 
		return orderNumber; 
	} 
	public void setOrderNumber(String orderNumber){ 
		 this.orderNumber = orderNumber;
	} 

	public String getPhoneNumber(){ 
		return phoneNumber; 
	} 
	public void setPhoneNumber(String phoneNumber){ 
		 this.phoneNumber = phoneNumber;
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

	public String getRecipientPhoneNumber(){ 
		return recipientPhoneNumber; 
	} 
	public void setRecipientPhoneNumber(String recipientPhoneNumber){ 
		 this.recipientPhoneNumber = recipientPhoneNumber;
	} 

	public String getRecipientLastname(){ 
		return recipientLastname; 
	} 
	public void setRecipientLastname(String recipientLastname){ 
		 this.recipientLastname = recipientLastname;
	} 

	public String getRecipientFirstname(){ 
		return recipientFirstname; 
	} 
	public void setRecipientFirstname(String recipientFirstname){ 
		 this.recipientFirstname = recipientFirstname;
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

	public String getZipcode(){ 
		return zipcode; 
	} 
	public void setZipcode(String zipcode){ 
		 this.zipcode = zipcode;
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

}