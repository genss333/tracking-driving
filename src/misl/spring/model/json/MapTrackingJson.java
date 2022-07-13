package misl.spring.model.json;

import java.util.ArrayList;

import lombok.Data;

@Data
public class MapTrackingJson {
	private ArrayList<double[]> polyLine;
	private ArrayList<double[][]> dashedLine;
	private String endTime;
}
