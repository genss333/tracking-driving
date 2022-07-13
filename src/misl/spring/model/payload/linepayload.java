package misl.spring.model.payload;

import java.util.ArrayList;

import lombok.Data;

@Data
public class linepayload {
	private String[][]  dashline;
	private ArrayList<double[]>  polyline;
}
