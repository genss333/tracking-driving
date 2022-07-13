package misl.spring.model.payload;

import lombok.Data;

@Data
public class TimeTrackingPayload {
	private String starttime;
	private String endtime;
	private String status;
	private int id;
}
