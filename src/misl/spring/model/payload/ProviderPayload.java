package misl.spring.model.payload;

import lombok.Data;

@Data
public class ProviderPayload {
	private String providername; 
	private String address; 
	private String tambon; 
	private String amphur; 
	private String province; 
	private double latitude; 
	private double longitude;
	private String name;
	private String surname;
	private String email;
	private String phone;
}
