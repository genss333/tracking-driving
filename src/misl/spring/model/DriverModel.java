package misl.spring.model;

public class DriverModel{

	private int driverId; 
	private String username; 
	private String password; 
	private String firstname; 
	private String lastname; 
	private String address; 
	private String birthday; 
	private String phonenumber; 
	private String img; 
	private String driverDevice; 
	private String netpackage; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "DriverModel [driverId=" + driverId + ", username=" + username + ", password=" + password + ", " + 
 			   "firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", " + 
 			   "birthday=" + birthday + ", phonenumber=" + phonenumber + ", img=" + img + ", " + 
 			   "driverDevice=" + driverDevice + ", netpackage=" + netpackage + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getDriverId(){ 
		return driverId; 
	} 
	public void setDriverId(int driverId){ 
		 this.driverId = driverId;
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

	public String getBirthday(){ 
		return birthday; 
	} 
	public void setBirthday(String birthday){ 
		 this.birthday = birthday;
	} 

	public String getPhonenumber(){ 
		return phonenumber; 
	} 
	public void setPhonenumber(String phonenumber){ 
		 this.phonenumber = phonenumber;
	} 

	public String getImg(){ 
		return img; 
	} 
	public void setImg(String img){ 
		 this.img = img;
	} 

	public String getDriverDevice(){ 
		return driverDevice; 
	} 
	public void setDriverDevice(String driverDevice){ 
		 this.driverDevice = driverDevice;
	} 

	public String getNetpackage(){ 
		return netpackage; 
	} 
	public void setNetpackage(String netpackage){ 
		 this.netpackage = netpackage;
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