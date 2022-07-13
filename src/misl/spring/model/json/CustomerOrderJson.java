package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;
import misl.spring.model.payload.OrdersRouteJson;

@Data
public class CustomerOrderJson {
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String Address;
	private double CustomerLat;
	private double CustomerLng;
	private String status;
	private ArrayList<OrdersRouteJson> OrderRouteId;
}
