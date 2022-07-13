package misl.spring.model.json;

import lombok.Data;

@Data
public class UserJson {
	private int userid;
	private String address; 
	private String province; 
	private String amphur; 
	private String tambon; 
	private int zipcode;
	private String lat; 
	private String lng;
	private String phoneNumber;
	private String firstname; 
	private String lastname; 
	private String email; 
}
