package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;
import misl.spring.model.VehicleModel;

@Data
public class WayPointVehicleJson {
	private DriverJson driver;
	private VehicleModel vehicle;
	private ArrayList<OrdersVehicleJson> order;
	private double distance ;
}
