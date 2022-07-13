package misl.spring.model.payload;

import lombok.Data;

@Data
public class RouteOrderPayload {
	private int vehicleJobSessionId; 
	private int orderRouteId; 
	private double lat;
	private double lng;
}
