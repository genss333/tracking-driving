package misl.spring.model.json;

import lombok.Data;

@Data
public class LocationDriverJson {
	private int DriverId;
	private String DriverName;
	private String ParcelNumber;
	private String PhoneNumber;
	private float Distance;
	private float Duration;
	private double DriverLat;
	private double DriverLng;
	private double[][] Route;
	
}
