package misl.spring.model;

public class UsersLocationModel{

	private int userlocationid; 
	private String address; 
	private String province; 
	private String amphur; 
	private String tambon; 
	private int zipcode; 
	private UsersModel userid; 
	private String lat; 
	private String lng; 

	@Override
	public String toString() {

		return "UsersLocationModel [userlocationid=" + userlocationid + ", address=" + address + ", province=" + province + ", " + 
 			   "amphur=" + amphur + ", tambon=" + tambon + ", zipcode=" + zipcode + ", " + 
 			   "userid=" + userid + ", lat=" + lat + ", lng=" + lng + "]";
	} 

	public int getUserlocationid(){ 
		return userlocationid; 
	} 
	public void setUserlocationid(int userlocationid){ 
		 this.userlocationid = userlocationid;
	} 

	public String getAddress(){ 
		return address; 
	} 
	public void setAddress(String address){ 
		 this.address = address;
	} 

	public String getProvince(){ 
		return province; 
	} 
	public void setProvince(String province){ 
		 this.province = province;
	} 

	public String getAmphur(){ 
		return amphur; 
	} 
	public void setAmphur(String amphur){ 
		 this.amphur = amphur;
	} 

	public String getTambon(){ 
		return tambon; 
	} 
	public void setTambon(String tambon){ 
		 this.tambon = tambon;
	} 

	public int getZipcode(){ 
		return zipcode; 
	} 
	public void setZipcode(int zipcode){ 
		 this.zipcode = zipcode;
	} 

	public UsersModel getUserid(){ 
		return userid; 
	} 
	public void setUserid(UsersModel userid){ 
		 this.userid = userid;
	} 

	public String getLat(){ 
		return lat; 
	} 
	public void setLat(String lat){ 
		 this.lat = lat;
	} 

	public String getLng(){ 
		return lng; 
	} 
	public void setLng(String lng){ 
		 this.lng = lng;
	} 

}