package misl.spring.model.payload;

import lombok.Data;

@Data
public class CustomerLocation {
	int order_id;
	double customer_lat;
	double customer_lng;
	double driver_lat;
	double driver_lng;
}
