package misl.spring.model.payload;

import lombok.Data;

@Data
public class OrdersRouteJson{
	private int orderId;
	private int orderRouteId; 
	private int vehicleJobSessionId;
	private int index;
	private String ParcelNumbe;
	private double lat;
	private double lng;
	
	
	
}