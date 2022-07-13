package misl.spring.model.payload;

import lombok.Data;

@Data
public class orderpayload {
	private int sellerId; 
	private String orderNumber; 
	private String phoneNumber; 
	private String subPhoneNumber; 
	private String firstname; 
	private String lastname; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private String zipcode; 
	private double latitude; 
	private double longitude; 
}
