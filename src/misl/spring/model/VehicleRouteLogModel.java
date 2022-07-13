package misl.spring.model;

public class VehicleRouteLogModel{

	private int vehicleRouteLogId; 
	private OrdersRouteModel orderRouteId; 
	private double latitude; 
	private double longitude; 
	private String datetimeRecord; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "VehicleRouteLogModel [vehicleRouteLogId=" + vehicleRouteLogId + ", orderRouteId=" + orderRouteId + ", latitude=" + latitude + ", " + 
 			   "longitude=" + longitude + ", datetimeRecord=" + datetimeRecord + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getVehicleRouteLogId(){ 
		return vehicleRouteLogId; 
	} 
	public void setVehicleRouteLogId(int vehicleRouteLogId){ 
		 this.vehicleRouteLogId = vehicleRouteLogId;
	} 

	public OrdersRouteModel getOrderRouteId(){ 
		return orderRouteId; 
	} 
	public void setOrderRouteId(OrdersRouteModel orderRouteId){ 
		 this.orderRouteId = orderRouteId;
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

	public String getDatetimeRecord(){ 
		return datetimeRecord; 
	} 
	public void setDatetimeRecord(String datetimeRecord){ 
		 this.datetimeRecord = datetimeRecord;
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