package misl.spring.model.payload;

import lombok.Data;

@Data
public class AddLocationPayload {
	private double lat;
	private double lng;
	private int orderRouteId;
	private String datetimeRecord; 
}
