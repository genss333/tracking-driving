package misl.spring.model.json;

import lombok.Data;
import misl.spring.model.EmployeeModel;
import misl.spring.model.ProviderModel;

@Data
public class CompanyAdminJson {
	private EmployeeModel employeeModel;
	private ProviderModel providerModel;
}
