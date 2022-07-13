package misl.spring.model;

public class ZoneModel{

	private int zoneId; 
	private ProviderModel providerId; 
	private VehicleModel vehicleId; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "ZoneModel [zoneId=" + zoneId + ", providerId=" + providerId + ", vehicleId=" + vehicleId + ", " + 
 			   "tambon=" + tambon + ", amphur=" + amphur + ", province=" + province + ", " + 
 			   "createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

	public int getZoneId(){ 
		return zoneId; 
	} 
	public void setZoneId(int zoneId){ 
		 this.zoneId = zoneId;
	} 

	public ProviderModel getProviderId(){ 
		return providerId; 
	} 
	public void setProviderId(ProviderModel providerId){ 
		 this.providerId = providerId;
	} 

	public VehicleModel getVehicleId(){ 
		return vehicleId; 
	} 
	public void setVehicleId(VehicleModel vehicleId){ 
		 this.vehicleId = vehicleId;
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