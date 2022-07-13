package misl.spring.model;

public class VehicleJobSessionModel{

	private int vehicleJobSessionId; 
	private StatusSessionModel statusId; 
	private DriverModel driverId; 
	private VehicleModel vehicleId; 
	private String dateRoute; 
	private String startTime; 
	private String endTime; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "VehicleJobSessionModel [vehicleJobSessionId=" + vehicleJobSessionId + ", statusId=" + statusId + ", driverId=" + driverId + ", " + 
 			   "vehicleId=" + vehicleId + ", dateRoute=" + dateRoute + ", startTime=" + startTime + ", " + 
 			   "endTime=" + endTime + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

	public int getVehicleJobSessionId(){ 
		return vehicleJobSessionId; 
	} 
	public void setVehicleJobSessionId(int vehicleJobSessionId){ 
		 this.vehicleJobSessionId = vehicleJobSessionId;
	} 

	public StatusSessionModel getStatusId(){ 
		return statusId; 
	} 
	public void setStatusId(StatusSessionModel statusId){ 
		 this.statusId = statusId;
	} 

	public DriverModel getDriverId(){ 
		return driverId; 
	} 
	public void setDriverId(DriverModel driverId){ 
		 this.driverId = driverId;
	} 

	public VehicleModel getVehicleId(){ 
		return vehicleId; 
	} 
	public void setVehicleId(VehicleModel vehicleId){ 
		 this.vehicleId = vehicleId;
	} 

	public String getDateRoute(){ 
		return dateRoute; 
	} 
	public void setDateRoute(String dateRoute){ 
		 this.dateRoute = dateRoute;
	} 

	public String getStartTime(){ 
		return startTime; 
	} 
	public void setStartTime(String startTime){ 
		 this.startTime = startTime;
	} 

	public String getEndTime(){ 
		return endTime; 
	} 
	public void setEndTime(String endTime){ 
		 this.endTime = endTime;
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