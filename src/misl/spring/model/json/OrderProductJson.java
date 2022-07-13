package misl.spring.model.json;

import lombok.Data;

@Data
public class OrderProductJson {
	private String sendDate;
	private String parcelNumber;
	private String driverName;
	private String owner;
	private String Status;
	private String provider;
}
