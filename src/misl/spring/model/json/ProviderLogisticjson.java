package misl.spring.model.json;

import lombok.Data;

@Data
public class ProviderLogisticjson {
	int jobsectionId;
	String date;
	String vehicle;
	String driver;
	int jobSuccessful;
	int jobAll;
	int jobFault;
	double distanceSuccessful;
	double distanceAll;
	double distanceFault;
	String status;
}
