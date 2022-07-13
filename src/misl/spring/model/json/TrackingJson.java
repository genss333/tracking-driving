package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;
@Data
public class TrackingJson {
	private DriverJson driver;
	private VehicleJson vehicle;
	private ArrayList<Location> Locations;
}
