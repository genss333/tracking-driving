package misl.spring.model.json;

import lombok.Data;

@Data
public class ResLocationJson {
	private String distance;
	private String duration;
	private String[][] TSP;
}
