package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.VehicleModel;
import misl.spring.model.extra.ResultMap;
import misl.spring.model.payload.vehiclepayload;
import misl.util.CheckNumber; 

public class VehicleDAO implements DAO<VehicleModel>{

	 private Database db;
	 private boolean inherit_model;

	 public VehicleDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(VehicleModel bean) throws SQLException {

		 String sql = "INSERT INTO vehicle(`name`, `fuel`, " + 
 					 "`brand`, `type`) VALUES(?, ?, " + 
 					 "?, ?)"; 
 
		 return db.add(sql, new String[]{"vehicle_id"}, bean.getName(), bean.getFuel(),  
 					 bean.getBrand(), bean.getType()); 
	 } 

	 @Override
	 public int Delete(VehicleModel bean) throws SQLException {

		 String sql = "DELETE FROM vehicle WHERE vehicle_id = ? "; 

		 return db.remove(sql,bean.getVehicleId()); 
	 } 

	 @Override
	 public int Update(VehicleModel bean) throws SQLException {

		 String sql = "UPDATE vehicle SET `name` = ?, `fuel` = ?, " + 
 					 "`brand` = ?, `type` = ? WHERE `vehicle_id` = ?"; 

		 return db.update(sql, bean.getName(), bean.getFuel(),  
 					 bean.getBrand(), bean.getType(), bean.getVehicleId()); 
	 } 

	 @Override
	 public ArrayList<VehicleModel> FindAll() throws SQLException {

		 ArrayList<VehicleModel> result = new ArrayList<VehicleModel>();

		 String sql = "SELECT * FROM vehicle ORDER BY vehicle_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleModel FindByID(VehicleModel bean) throws SQLException {

		 VehicleModel result = new VehicleModel();

		 String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getVehicleId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleModel FindByID(int id) throws SQLException {

		 VehicleModel result = new VehicleModel();

		 String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public VehicleModel MappingBeans(ResultMap map) throws SQLException {

		VehicleModel model = new VehicleModel(); 

		model.setVehicleId(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
		model.setName(map.get("name")); 
		model.setFuel(map.get("fuel")); 
		model.setBrand(map.get("brand")); 
		model.setType(map.get("type")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 }
	 public ArrayList<VehicleModel> FindVehicleIn(int id) throws SQLException {

		 ArrayList<VehicleModel> result = new ArrayList<VehicleModel>();

		 String sql = "SELECT * FROM vehicle WHERE "; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<vehiclepayload> FindVehicle(String name) throws SQLException {

			ArrayList<vehiclepayload> result = new ArrayList<vehiclepayload>();

			String sql = "SELECT provider.`name` as name_provider,vehicle.vehicle_id,provider.provider_id FROM provider INNER JOIN zone ON provider.provider_id = zone.provider_id INNER JOIN vehicle ON zone.vehicle_id =  vehicle.vehicle_id WHERE zone.AMPHUR =  " + "'" + name + "'";
			ArrayList<vehiclepayload> queryList = db.queryList2(sql);

			if (!queryList.isEmpty()) {
				for (vehiclepayload map : queryList) {
					result.add(map);
				}
			}

			return result;
		}
	 
}