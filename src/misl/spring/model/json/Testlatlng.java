package misl.spring.model.json;

import lombok.Data;

public class Testlatlng {
	private String lat;
	private String lng;
	public Testlatlng (String lat, String lng) {
		this.lat = lat;
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
}


