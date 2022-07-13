package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;

@Data
public class DriverWorkJson {
	private int vehicleJobSessionId; 
	private String statusId;
	private String date;
	private ArrayList<OrdersJson> works;
}
