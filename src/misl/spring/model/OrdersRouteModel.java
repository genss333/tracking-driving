package misl.spring.model;

public class OrdersRouteModel{

	private int orderRouteId; 
	private OrdersModel orderId; 
	private VehicleJobSessionModel vehicleRouteId; 
	private int index; 
	private String createdAt; 
	private String updatedAt; 
	private double distance; 
	private double duration; 
	private OrderRouteStatusModel orderRouteStatusId; 

	@Override
	public String toString() {

		return "OrdersRouteModel [orderRouteId=" + orderRouteId + ", orderId=" + orderId + ", vehicleRouteId=" + vehicleRouteId + ", " + 
 			   "index=" + index + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", " + 
 			   "distance=" + distance + ", duration=" + duration + ", orderRouteStatusId=" + orderRouteStatusId + "]";
	} 

	public int getOrderRouteId(){ 
		return orderRouteId; 
	} 
	public void setOrderRouteId(int orderRouteId){ 
		 this.orderRouteId = orderRouteId;
	} 

	public OrdersModel getOrderId(){ 
		return orderId; 
	} 
	public void setOrderId(OrdersModel orderId){ 
		 this.orderId = orderId;
	} 

	public VehicleJobSessionModel getVehicleRouteId(){ 
		return vehicleRouteId; 
	} 
	public void setVehicleRouteId(VehicleJobSessionModel vehicleRouteId){ 
		 this.vehicleRouteId = vehicleRouteId;
	} 

	public int getIndex(){ 
		return index; 
	} 
	public void setIndex(int index){ 
		 this.index = index;
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

	public double getDistance(){ 
		return distance; 
	} 
	public void setDistance(double distance){ 
		 this.distance = distance;
	} 

	public double getDuration(){ 
		return duration; 
	} 
	public void setDuration(double duration){ 
		 this.duration = duration;
	} 

	public OrderRouteStatusModel getOrderRouteStatusId(){ 
		return orderRouteStatusId; 
	} 
	public void setOrderRouteStatusId(OrderRouteStatusModel orderRouteStatusId){ 
		 this.orderRouteStatusId = orderRouteStatusId;
	} 

}