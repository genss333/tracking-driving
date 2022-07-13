package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.EmployeeModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.ProviderModel;

public class EmployeeDAO implements DAO<EmployeeModel>{

	 private Database db;
	 private boolean inherit_model;

	 public EmployeeDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(EmployeeModel bean) throws SQLException {

		 String sql = "INSERT INTO employee(`provider_id`, `username`, " + 
 					 "`password`, `firstname`, `lastname`, " + 
 					 "`phone_number`, `address`, `roleId`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?)"; 
 
		 return db.add(sql, new String[]{"employee_id"}, bean.getProviderId().getProviderId(), bean.getUsername(),  
 					bean.getPassword(), bean.getFirstname(), bean.getLastname(),  
 					bean.getPhoneNumber(), bean.getAddress(), CheckNumber.formatNull(bean.getRoleid())); 
	 } 

	 @Override
	 public int Delete(EmployeeModel bean) throws SQLException {

		 String sql = "DELETE FROM employee WHERE employee_id = ? "; 

		 return db.remove(sql,bean.getEmployeeId()); 
	 } 

	 @Override
	 public int Update(EmployeeModel bean) throws SQLException {

		 String sql = "UPDATE employee SET `provider_id` = ?, `username` = ?, " + 
 					 "`password` = ?, `firstname` = ?, `lastname` = ?, " + 
 					 "`phone_number` = ?, `address` = ?, `roleId` = ? WHERE `employee_id` = ?"; 

		 return db.update(sql, bean.getProviderId().getProviderId(), bean.getUsername(),  
 					bean.getPassword(), bean.getFirstname(), bean.getLastname(),  
 					bean.getPhoneNumber(), bean.getAddress(), CheckNumber.formatNull(bean.getRoleid()), bean.getEmployeeId()); 
	 } 

	 @Override
	 public ArrayList<EmployeeModel> FindAll() throws SQLException {

		 ArrayList<EmployeeModel> result = new ArrayList<EmployeeModel>();

		 String sql = "SELECT * FROM employee ORDER BY employee_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public EmployeeModel FindByID(EmployeeModel bean) throws SQLException {

		 EmployeeModel result = new EmployeeModel();

		 String sql = "SELECT * FROM employee WHERE employee_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getEmployeeId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public EmployeeModel FindByID(int id) throws SQLException {

		 EmployeeModel result = new EmployeeModel();

		 String sql = "SELECT * FROM employee WHERE employee_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public EmployeeModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public EmployeeModel MappingBeans(ResultMap map) throws SQLException {

		EmployeeModel model = new EmployeeModel(); 

		 if(inherit_model) {

			ProviderDAO providerDAO = new ProviderDAO(); 

			ProviderModel providerModel = providerDAO.FindByID(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

		 } else {

			ProviderModel providerModel = new ProviderModel(); 

			providerModel.setProviderId(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

		 }

		model.setEmployeeId(map.get("employee_id") != null ? Integer.parseInt(map.get("employee_id")) : Integer.MIN_VALUE); 
		model.setUsername(map.get("username")); 
		model.setPassword(map.get("password")); 
		model.setFirstname(map.get("firstname")); 
		model.setLastname(map.get("lastname")); 
		model.setPhoneNumber(map.get("phone_number")); 
		model.setAddress(map.get("address")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 
		model.setRoleid(map.get("roleId") != null ? Integer.parseInt(map.get("roleId")) : Integer.MIN_VALUE); 

		return model; 
	 }
	 public EmployeeModel FindByProviderId(int id) throws SQLException {

		 EmployeeModel result = new EmployeeModel();

		 String sql = "SELECT * FROM employee WHERE roleId = 1 AND provider_id = ? AND username LIKE  'admin%' LIMIT 1";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 }
}