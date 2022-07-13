package misl.spring.model;

public class OrderRouteStatusModel{

	private int orderRouteStatusId; 
	private String statusName; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "OrderRouteStatusModel [orderRouteStatusId=" + orderRouteStatusId + ", statusName=" + statusName + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getOrderRouteStatusId(){ 
		return orderRouteStatusId; 
	} 
	public void setOrderRouteStatusId(int orderRouteStatusId){ 
		 this.orderRouteStatusId = orderRouteStatusId;
	} 

	public String getStatusName(){ 
		return statusName; 
	} 
	public void setStatusName(String statusName){ 
		 this.statusName = statusName;
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