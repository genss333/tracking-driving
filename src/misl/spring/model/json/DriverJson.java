package misl.spring.model.json;

import lombok.Data;

@Data
public class DriverJson {
	private int driverId; 
	private String firstname; 
	private String lastname; 
	private String address; 
	private String birthday; 
	private String phonenumber; 
	private String img; 
	private String username;
}
