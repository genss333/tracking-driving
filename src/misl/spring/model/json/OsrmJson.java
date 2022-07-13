package misl.spring.model.json;

import lombok.Data;

@Data
public class OsrmJson {
	private int[] index;
	private String[][] TSPLocation;
	private String[][] location;
	private double[] distance;
	private double[] duration;
}
