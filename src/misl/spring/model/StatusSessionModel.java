package misl.spring.model;

public class StatusSessionModel{

	private int statusSessionId; 
	private String statusName; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "StatusSessionModel [statusSessionId=" + statusSessionId + ", statusName=" + statusName + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getStatusSessionId(){ 
		return statusSessionId; 
	} 
	public void setStatusSessionId(int statusSessionId){ 
		 this.statusSessionId = statusSessionId;
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