package misl.spring.model.json;

import lombok.Data;
import misl.spring.model.ProviderModel;
import misl.spring.model.VehicleModel;

@Data
public class Sender {
	private DriverJson driverJson;
	private ProviderModel providerModel;
	private VehicleModel vehicleModel;
}
