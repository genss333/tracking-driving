package misl.spring.model;

public class EmployeeModel{

	private int employeeId; 
	private ProviderModel providerId; 
	private String username; 
	private String password; 
	private String firstname; 
	private String lastname; 
	private String phoneNumber; 
	private String address; 
	private String createdAt; 
	private String updatedAt; 
	private int roleid; 

	@Override
	public String toString() {

		return "EmployeeModel [employeeId=" + employeeId + ", providerId=" + providerId + ", username=" + username + ", " + 
 			   "password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", " + 
 			   "phoneNumber=" + phoneNumber + ", address=" + address + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + ", roleid=" + roleid + "]";
	} 

	public int getEmployeeId(){ 
		return employeeId; 
	} 
	public void setEmployeeId(int employeeId){ 
		 this.employeeId = employeeId;
	} 

	public ProviderModel getProviderId(){ 
		return providerId; 
	} 
	public void setProviderId(ProviderModel providerId){ 
		 this.providerId = providerId;
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

	public String getPhoneNumber(){ 
		return phoneNumber; 
	} 
	public void setPhoneNumber(String phoneNumber){ 
		 this.phoneNumber = phoneNumber;
	} 

	public String getAddress(){ 
		return address; 
	} 
	public void setAddress(String address){ 
		 this.address = address;
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

	public int getRoleid(){ 
		return roleid; 
	} 
	public void setRoleid(int roleid){ 
		 this.roleid = roleid;
	} 

}