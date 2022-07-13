package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.ZoneModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.ProviderModel;
import misl.spring.model.VehicleModel;

public class ZoneDAO implements DAO<ZoneModel>{

	 private Database db;
	 private boolean inherit_model;

	 public ZoneDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(ZoneModel bean) throws SQLException {

		 String sql = "INSERT INTO zone(`provider_id`, `vehicle_id`, " + 
 					 "`TAMBON`, `AMPHUR`, `province`) VALUES(?, ?, " + 
 					 "?, ?, ?)"; 
 
		 return db.add(sql, new String[]{"zone_id"}, bean.getProviderId().getProviderId(), bean.getVehicleId().getVehicleId(),  
 					bean.getTambon(), bean.getAmphur(), bean.getProvince()); 
	 } 

	 @Override
	 public int Delete(ZoneModel bean) throws SQLException {

		 String sql = "DELETE FROM zone WHERE zone_id = ? "; 

		 return db.remove(sql,bean.getZoneId()); 
	 } 

	 @Override
	 public int Update(ZoneModel bean) throws SQLException {

		 String sql = "UPDATE zone SET `provider_id` = ?, `vehicle_id` = ?, " + 
 					 "`TAMBON` = ?, `AMPHUR` = ?, `province` = ? WHERE `zone_id` = ?"; 

		 return db.update(sql, bean.getProviderId().getProviderId(), bean.getVehicleId().getVehicleId(),  
 					bean.getTambon(), bean.getAmphur(), bean.getProvince(), bean.getZoneId()); 
	 } 

	 @Override
	 public ArrayList<ZoneModel> FindAll() throws SQLException {

		 ArrayList<ZoneModel> result = new ArrayList<ZoneModel>();

		 String sql = "SELECT * FROM zone ORDER BY zone_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public ZoneModel FindByID(ZoneModel bean) throws SQLException {

		 ZoneModel result = new ZoneModel();

		 String sql = "SELECT * FROM zone WHERE zone_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getZoneId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public ZoneModel FindByID(int id) throws SQLException {

		 ZoneModel result = new ZoneModel();

		 String sql = "SELECT * FROM zone WHERE zone_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public ZoneModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public ZoneModel MappingBeans(ResultMap map) throws SQLException {

		ZoneModel model = new ZoneModel(); 

		 if(inherit_model) {

			ProviderDAO providerDAO = new ProviderDAO(); 
			VehicleDAO vehicleDAO = new VehicleDAO(); 

			ProviderModel providerModel = providerDAO.FindByID(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

			VehicleModel vehicleModel = vehicleDAO.FindByID(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
			model.setVehicleId(vehicleModel); 

		 } else {

			ProviderModel providerModel = new ProviderModel(); 
			VehicleModel vehicleModel = new VehicleModel(); 

			providerModel.setProviderId(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

			vehicleModel.setVehicleId(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
			model.setVehicleId(vehicleModel); 

		 }

		model.setZoneId(map.get("zone_id") != null ? Integer.parseInt(map.get("zone_id")) : Integer.MIN_VALUE); 
		model.setTambon(map.get("TAMBON")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setProvince(map.get("province")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 }
	 public ArrayList<ZoneModel> FindByProvider(int id) throws SQLException {

		 ArrayList<ZoneModel> result = new ArrayList<ZoneModel>();

		 String sql = "SELECT * FROM zone WHERE provider_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, id); 
		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<ZoneModel> FindByVehicle(int id) throws SQLException {

		 ArrayList<ZoneModel> result = new ArrayList<ZoneModel>();

		 String sql = "SELECT * FROM zone WHERE vehicle_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, id); 
		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ZoneModel FindByVehicleJobSessionID(int id) throws SQLException {

		 ZoneModel result = new ZoneModel();

		 String sql = "SELECT z.*\r\n"
		 		+ "FROM vehicle_job_session js, zone z, vehicle v\r\n"
		 		+ "WHERE js.vehicle_id = v.vehicle_id\r\n"
		 		+ "AND js.vehicle_id = z.vehicle_id\r\n"
		 		+ "AND js.Vehicle_Job_Session_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

}