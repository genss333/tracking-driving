package misl.spring.model;

public class ThmapModel{

	private int thmapid; 
	private String province; 
	private String amphur; 
	private String tambon; 
	private String lat; 
	private String lng; 
	private int zipcode; 

	@Override
	public String toString() {

		return "ThmapModel [thmapid=" + thmapid + ", province=" + province + ", amphur=" + amphur + ", " + 
 			   "tambon=" + tambon + ", lat=" + lat + ", lng=" + lng + ", " + 
 			   "zipcode=" + zipcode + "]";
	} 

	public int getThmapid(){ 
		return thmapid; 
	} 
	public void setThmapid(int thmapid){ 
		 this.thmapid = thmapid;
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

	public int getZipcode(){ 
		return zipcode; 
	} 
	public void setZipcode(int zipcode){ 
		 this.zipcode = zipcode;
	} 

}