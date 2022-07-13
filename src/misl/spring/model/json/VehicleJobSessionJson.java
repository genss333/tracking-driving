package misl.spring.model.json;

import lombok.Data;
import misl.spring.model.VehicleModel;

@Data
public class VehicleJobSessionJson {
	private int vehicleJobSessionId; 
	private VehicleModel vehicleId; 
	private String dateRoute;
	
}
