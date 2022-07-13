package misl.spring.model.json;

import lombok.Data;
import misl.spring.model.ProviderModel;

@Data
public class OrdersModelJson {
	private int orderId; 
	private String orderNumber; 
	private String phoneNumber; 
	private String subPhoneNumber; 
	private String firstname; 
	private String lastname; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province;
	private ProviderModel providerModel;
	private String date;
	
}
