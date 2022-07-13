package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;
import misl.spring.model.VehicleModel;
import misl.spring.model.ZoneModel;

@Data
public class RouteJson {
	private ZoneModel zone;
	private VehicleModel vehicle;
	private ArrayList<OrdersJson> order;
	private String data;
	private double distance;
	private int countjob;
	private int countsussessjob;
}
