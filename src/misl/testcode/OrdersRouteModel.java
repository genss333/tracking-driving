package misl.testcode;

public class OrdersRouteModel implements Comparable<OrdersRouteModel>{

	private int orderRouteId; 
	private int index;
	private String lat;
	private String lng;
	private double distance;
	private double duration;
	public int getOrderRouteId() {
		return orderRouteId;
	}
	public void setOrderRouteId(int orderRouteId) {
		this.orderRouteId = orderRouteId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	@Override
	public int compareTo(OrdersRouteModel o) {
		// TODO Auto-generated method stub
		return this.index - o.index;
	}
	
	
}