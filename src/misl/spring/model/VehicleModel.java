package misl.spring.model;

public class VehicleModel{

	private int vehicleId; 
	private String name; 
	private String fuel; 
	private String brand; 
	private String type; 
	private String createdAt; 
	private String updatedAt; 

	@Override
	public String toString() {

		return "VehicleModel [vehicleId=" + vehicleId + ", name=" + name + ", fuel=" + fuel + ", " + 
 			   "brand=" + brand + ", type=" + type + ", createdAt=" + createdAt + ", " + 
 			   "updatedAt=" + updatedAt + "]";
	} 

	public int getVehicleId(){ 
		return vehicleId; 
	} 
	public void setVehicleId(int vehicleId){ 
		 this.vehicleId = vehicleId;
	} 

	public String getName(){ 
		return name; 
	} 
	public void setName(String name){ 
		 this.name = name;
	} 

	public String getFuel(){ 
		return fuel; 
	} 
	public void setFuel(String fuel){ 
		 this.fuel = fuel;
	} 

	public String getBrand(){ 
		return brand; 
	} 
	public void setBrand(String brand){ 
		 this.brand = brand;
	} 

	public String getType(){ 
		return type; 
	} 
	public void setType(String type){ 
		 this.type = type;
	} 

	public String getCreatedAt(){ 
		return createdAt; 
	} 
	public void setCreatedAt(String createdAt){ 
		 this.createdAt = createdAt;
	} 

	public String getUpdatedAt(){ 
		return updatedAt; 
	} 
	public void setUpdatedAt(String updatedAt){ 
		 this.updatedAt = updatedAt;
	} 

}