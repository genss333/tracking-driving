package misl.spring.model;

public class OwnerModel{

	private int ownOrderId; 
	private ProviderModel providerId; 
	private OrdersModel orderId; 
	private String date; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "OwnerModel [ownOrderId=" + ownOrderId + ", providerId=" + providerId + ", orderId=" + orderId + ", " + 
 			   "date=" + date + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 

	public int getOwnOrderId(){ 
		return ownOrderId; 
	} 
	public void setOwnOrderId(int ownOrderId){ 
		 this.ownOrderId = ownOrderId;
	} 

	public ProviderModel getProviderId(){ 
		return providerId; 
	} 
	public void setProviderId(ProviderModel providerId){ 
		 this.providerId = providerId;
	} 

	public OrdersModel getOrderId(){ 
		return orderId; 
	} 
	public void setOrderId(OrdersModel orderId){ 
		 this.orderId = orderId;
	} 

	public String getDate(){ 
		return date; 
	} 
	public void setDate(String date){ 
		 this.date = date;
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