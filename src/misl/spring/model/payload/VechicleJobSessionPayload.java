package misl.spring.model.payload;

import lombok.Data;
import misl.spring.model.DriverModel;
import misl.spring.model.StatusSessionModel;
import misl.spring.model.VehicleModel;

@Data
public class VechicleJobSessionPayload {
	private int vehicleJobSessionId; 
	private int statusSessionId;
	private int driverId; 
	private int vehicleId; 
	private String dateRoute; 
	private String startTime; 
	private String endTime; 
	private String createdAt; 
	private String updatedAt; 

}
