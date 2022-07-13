package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;

@Data
public class OrdersVehicleJson {
	private int orderId; 
	private String orderNumber; 
	private String phoneNumber; 
	private String firstname; 
	private String lastname;
	private String recipientPhoneNumber; 
	private String recipientLastname; 
	private String recipientFirstname; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province;
	private double lat;
	private double lng;
	private String date;
	private int index;
	private double[][] location;
	private String status;
}
