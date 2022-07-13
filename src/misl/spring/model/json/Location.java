package misl.spring.model.json;

import lombok.Data;

@Data
public class Location {
	private double latitude; 
	private double longitude; 
	private String datetimeRecord; 
}
