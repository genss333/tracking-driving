package misl.spring.model.payload;

import lombok.Data;

@Data
public class IndexOrderRoute {
	private int orderRouteId; 
	private double latitude; 
	private double longitude; 
}
