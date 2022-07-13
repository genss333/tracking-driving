package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.ProviderModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class ProviderDAO implements DAO<ProviderModel>{

	 private Database db;
	 private boolean inherit_model;

	 public ProviderDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(ProviderModel bean) throws SQLException {

		 String sql = "INSERT INTO provider(`name`, `address`, " + 
 					 "`TAMBON`, `AMPHUR`, `province`, " + 
 					 "`latitude`, `longitude`, `provider_status`, " + 
 					 "`email`, `phone`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?)"; 
 
		 return db.add(sql, new String[]{"provider_id"}, bean.getName(), bean.getAddress(),  
 					 bean.getTambon(), bean.getAmphur(), bean.getProvince(),  
 					 CheckNumber.formatNull(bean.getLatitude()), CheckNumber.formatNull(bean.getLongitude()), CheckNumber.formatNull(bean.getProviderStatus()),  
 					 bean.getEmail(), bean.getPhone()); 
	 } 

	 @Override
	 public int Delete(ProviderModel bean) throws SQLException {

		 String sql = "DELETE FROM provider WHERE provider_id = ? "; 

		 return db.remove(sql,bean.getProviderId()); 
	 } 

	 @Override
	 public int Update(ProviderModel bean) throws SQLException {

		 String sql = "UPDATE provider SET `name` = ?, `address` = ?, " + 
 					 "`TAMBON` = ?, `AMPHUR` = ?, `province` = ?, " + 
 					 "`latitude` = ?, `longitude` = ?, `provider_status` = ?, " + 
 					 "`email` = ?, `phone` = ? WHERE `provider_id` = ?"; 

		 return db.update(sql, bean.getName(), bean.getAddress(),  
 					 bean.getTambon(), bean.getAmphur(), bean.getProvince(),  
 					 CheckNumber.formatNull(bean.getLatitude()), CheckNumber.formatNull(bean.getLongitude()), CheckNumber.formatNull(bean.getProviderStatus()),  
 					 bean.getEmail(), bean.getPhone(), bean.getProviderId()); 
	 } 

	 @Override
	 public ArrayList<ProviderModel> FindAll() throws SQLException {

		 ArrayList<ProviderModel> result = new ArrayList<ProviderModel>();

		 String sql = "SELECT * FROM provider ORDER BY provider_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public ProviderModel FindByID(ProviderModel bean) throws SQLException {

		 ProviderModel result = new ProviderModel();

		 String sql = "SELECT * FROM provider WHERE provider_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getProviderId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public ProviderModel FindByID(int id) throws SQLException {

		 ProviderModel result = new ProviderModel();

		 String sql = "SELECT * FROM provider WHERE provider_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public ProviderModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public ProviderModel MappingBeans(ResultMap map) throws SQLException {

		ProviderModel model = new ProviderModel(); 

		model.setProviderId(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
		model.setName(map.get("name")); 
		model.setAddress(map.get("address")); 
		model.setTambon(map.get("TAMBON")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setProvince(map.get("province")); 
		model.setLatitude(map.get("latitude") != null ? Double.parseDouble(map.get("latitude")) : Double.MIN_VALUE); 
		model.setLongitude(map.get("longitude") != null ? Double.parseDouble(map.get("longitude")) : Double.MIN_VALUE); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 
		model.setProviderStatus(map.get("provider_status") != null ? Integer.parseInt(map.get("provider_status")) : Integer.MIN_VALUE); 
		model.setEmail(map.get("email")); 
		model.setPhone(map.get("phone")); 

		return model; 
	 } 
	 public ProviderModel FindByVehicle(int id) throws SQLException {

		 ProviderModel result = new ProviderModel();

		 String sql = "SELECT p.*\n" + 
		 		"FROM zone z, provider p\n" + 
		 		"WHERE z.provider_id = p.provider_id\n" + 
		 		"AND z.vehicle_id = ? \n" + 
		 		"LIMIT 1";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 }
	 public ArrayList<ProviderModel> FindAllCompany(int status) throws SQLException {

		 ArrayList<ProviderModel> result = new ArrayList<ProviderModel>();

		 String sql = "SELECT * FROM provider WHERE provider_status = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, status); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
}