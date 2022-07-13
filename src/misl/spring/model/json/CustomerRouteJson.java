package misl.spring.model.json;

import lombok.Data;

@Data
public class CustomerRouteJson {
	private double[][] TSPLocation;
	private int driverId;
	private int orderId;
	private String driverName;
	private String parcelNumber;
	private String phoneNumber;
	private double lat;
	private double lng;
	private double customerLat;
	private double customerLng;
	private double distance;
	private double duration;
	private String updateStatus;
	private String durationForm;
	

}
