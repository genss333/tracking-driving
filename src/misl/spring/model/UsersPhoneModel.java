package misl.spring.model;

public class UsersPhoneModel{

	private int userphoneid; 
	private String phoneNumber; 
	private UsersModel userid; 

	@Override
	public String toString() {

		return "UsersPhoneModel [userphoneid=" + userphoneid + ", phoneNumber=" + phoneNumber + ", userid=" + userid + "]";
	} 

	public int getUserphoneid(){ 
		return userphoneid; 
	} 
	public void setUserphoneid(int userphoneid){ 
		 this.userphoneid = userphoneid;
	} 

	public String getPhoneNumber(){ 
		return phoneNumber; 
	} 
	public void setPhoneNumber(String phoneNumber){ 
		 this.phoneNumber = phoneNumber;
	} 

	public UsersModel getUserid(){ 
		return userid; 
	} 
	public void setUserid(UsersModel userid){ 
		 this.userid = userid;
	} 

}