package misl.spring.model.payload;

import lombok.Data;

@Data
public class CustomerOrderPayload {
	private int orderId; 
	String orderDate;
	String orderNumber;
	String driver;
	String customer;
	String orderStatus;
	String orderProvider;
}
