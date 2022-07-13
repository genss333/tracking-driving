package misl.spring.model.payload;

import lombok.Data;
import misl.spring.model.ProviderModel;
import misl.spring.model.VehicleModel;

@Data
public class ZonePayload {
	private int zoneId; 
	private int vehicleId; 
	private int providerId; 
	private String tambon; 
	private String amphur; 
	private String province; 
}
