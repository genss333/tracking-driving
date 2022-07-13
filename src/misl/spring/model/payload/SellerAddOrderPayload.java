package misl.spring.model.payload;

import lombok.Data;

@Data
public class SellerAddOrderPayload {
	int providerId;
	String date;
	String logisticName;
	int jobSuccessful;
	int jobAll;
	int jobFault;
	double distanceSuccessful;
	double distanceAll;
	double distanceFault;
}
