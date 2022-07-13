package misl.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.DriverModel;
import misl.spring.model.StatusSessionModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.VehicleModel;
import misl.spring.model.extra.ResultMap;
import misl.spring.model.json.ProviderLogisticjson;
import misl.spring.model.payload.VechicleJobSessionPayload;

public class VehicleJobSessionDAO implements DAO<VehicleJobSessionModel>{

	 private Database db;
	 private boolean inherit_model;

	 public VehicleJobSessionDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(VehicleJobSessionModel bean) throws SQLException {

		 String sql = "INSERT INTO vehicle_job_session(`status_id`, `driver_id`, " + 
 					 "`vehicle_id`, `date_route`, `start_time`, " + 
 					 "`end_time`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"Vehicle_Job_Session_id"}, bean.getStatusId().getStatusSessionId(), bean.getDriverId().getDriverId(),  
 					bean.getVehicleId().getVehicleId(), bean.getDateRoute(), bean.getStartTime(),  
 					bean.getEndTime()); 
	 } 
	 public int Addjob(VechicleJobSessionPayload bean) throws SQLException {

		 String sql = "INSERT INTO vehicle_job_session(`status_id`, `driver_id`, " + 
 					 "`vehicle_id`, `date_route`, `start_time`, " + 
 					 "`end_time`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"Vehicle_Job_Session_id"}, bean.getStatusSessionId(), bean.getDriverId(),  
 					bean.getVehicleId(), bean.getDateRoute(), bean.getStartTime(),  
 					bean.getEndTime()); 
	 } 


	 @Override
	 public int Delete(VehicleJobSessionModel bean) throws SQLException {

		 String sql = "DELETE FROM vehicle_job_session WHERE Vehicle_Job_Session_id = ? "; 

		 return db.remove(sql,bean.getVehicleJobSessionId()); 
	 } 

	 @Override
	 public int Update(VehicleJobSessionModel bean) throws SQLException {

		 String sql = "UPDATE vehicle_job_session SET `status_id` = ?, `driver_id` = ?, " + 
 					 "`vehicle_id` = ?, `date_route` = ?, `start_time` = ?, " + 
 					 "`end_time` = ? WHERE `Vehicle_Job_Session_id` = ?"; 

		 return db.update(sql, bean.getStatusId().getStatusSessionId(), bean.getDriverId().getDriverId(),  
 					bean.getVehicleId().getVehicleId(), bean.getDateRoute(), bean.getStartTime(),  
 					bean.getEndTime(), bean.getVehicleJobSessionId()); 
	 } 

	 @Override
	 public ArrayList<VehicleJobSessionModel> FindAll() throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session ORDER BY Vehicle_Job_Session_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleJobSessionModel FindByID(VehicleJobSessionModel bean) throws SQLException {

		 VehicleJobSessionModel result = new VehicleJobSessionModel();

		 String sql = "SELECT * FROM vehicle_job_session WHERE Vehicle_Job_Session_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getVehicleJobSessionId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleJobSessionModel FindByID(int id) throws SQLException {

		 VehicleJobSessionModel result = new VehicleJobSessionModel();

		 String sql = "SELECT * FROM vehicle_job_session WHERE Vehicle_Job_Session_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleJobSessionModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public VehicleJobSessionModel MappingBeans(ResultMap map) throws SQLException {

		VehicleJobSessionModel model = new VehicleJobSessionModel(); 

		 if(inherit_model) {

			StatusSessionDAO statusSessionDAO = new StatusSessionDAO(); 
			DriverDAO driverDAO = new DriverDAO(); 
			VehicleDAO vehicleDAO = new VehicleDAO(); 

			StatusSessionModel statusSessionModel = statusSessionDAO.FindByID(map.get("status_id") != null ? Integer.parseInt(map.get("status_id")) : Integer.MIN_VALUE); 
			model.setStatusId(statusSessionModel); 

			DriverModel driverModel = driverDAO.FindByID(map.get("driver_id") != null ? Integer.parseInt(map.get("driver_id")) : Integer.MIN_VALUE); 
			model.setDriverId(driverModel); 

			VehicleModel vehicleModel = vehicleDAO.FindByID(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
			model.setVehicleId(vehicleModel); 

		 } else {

			StatusSessionModel statusSessionModel = new StatusSessionModel(); 
			DriverModel driverModel = new DriverModel(); 
			VehicleModel vehicleModel = new VehicleModel(); 

			statusSessionModel.setStatusSessionId(map.get("status_id") != null ? Integer.parseInt(map.get("status_id")) : Integer.MIN_VALUE); 
			model.setStatusId(statusSessionModel); 

			driverModel.setDriverId(map.get("driver_id") != null ? Integer.parseInt(map.get("driver_id")) : Integer.MIN_VALUE); 
			model.setDriverId(driverModel); 

			vehicleModel.setVehicleId(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
			model.setVehicleId(vehicleModel); 

		 }

		model.setVehicleJobSessionId(map.get("Vehicle_Job_Session_id") != null ? Integer.parseInt(map.get("Vehicle_Job_Session_id")) : Integer.MIN_VALUE); 
		model.setDateRoute(map.get("date_route")); 
		model.setStartTime(map.get("start_time")); 
		model.setEndTime(map.get("end_time")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 }
	 public ArrayList<VehicleJobSessionModel> FindByVehicle(int id) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session WHERE vehicle_id = ? order by date_route"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<VehicleJobSessionModel> FindByUser(int id) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session WHERE driver_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<VehicleJobSessionModel> FindByStatus(int id) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session WHERE status_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<VehicleJobSessionModel> CountVecicle() throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT COUNT(vehicle_route_id) FROM orders_route WHERE vehicle_route_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public VehicleJobSessionModel FindByVehicleID(int id) throws SQLException {

		 VehicleJobSessionModel result = new VehicleJobSessionModel();

		 String sql = "SELECT * FROM vehicle_job_session WHERE vehicle_id = ? ";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public VehicleJobSessionModel FindByVehicleIDandDATE(int id,String date) throws SQLException {

		 VehicleJobSessionModel result = new VehicleJobSessionModel();

		 String sql = "SELECT * FROM vehicle_job_session WHERE vehicle_id = ? and date_route= ?";
		 ResultMap querySingle = db.querySingle(sql, id,date); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 }
		 return result; 
	 } 
	 public ArrayList<VehicleJobSessionModel> FindAllJob(int vehicleId,String date) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session WHERE vehicle_id = ? and date_route = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,vehicleId,date); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public ArrayList<VehicleJobSessionModel> FindAllJob(int id) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();

		 String sql = "SELECT * FROM vehicle_job_session where Vehicle_Job_Session_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 
	 public VehicleJobSessionModel FindByDriverIdandDate(int id,String date) throws SQLException {

		 VehicleJobSessionModel result = new VehicleJobSessionModel();

		 String sql = "SELECT * FROM vehicle_job_session WHERE driver_id = ? and date_route= ? AND status_id > 1";
		 ResultMap querySingle = db.querySingle(sql, id,date); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 }
		 return result; 
	 }
	 
	 public ArrayList<ResultMap> FindVehiclesByProvider(int id) throws SQLException {

		 ArrayList<VehicleJobSessionModel> result = new ArrayList<VehicleJobSessionModel>();
		 ArrayList<ProviderLogisticjson> result1 = new ArrayList<ProviderLogisticjson>();
		 String sql = "SELECT vehicle_job_session.*,\r\n" + 
		 		"		 		(SELECT COUNT(*) FROM orders_route WHERE vehicle_route_id = vehicle_job_session.Vehicle_Job_Session_id) AS alljob,\r\n" + 
		 		"		 		(SELECT COUNT(*) FROM orders_route WHERE vehicle_route_id = vehicle_job_session.Vehicle_Job_Session_id AND order_route_status_id > 1) AS successjob, \r\n" + 
		 		"		 		(SELECT COUNT(*) FROM orders_route WHERE vehicle_route_id = vehicle_job_session.Vehicle_Job_Session_id AND order_route_status_id = 1) AS leftjob,\r\n" + 
		 		"				cast((SELECT SUM(distance) FROM orders_route WHERE vehicle_route_id = vehicle_job_session.Vehicle_Job_Session_id) as decimal(8,1))AS distanceall\r\n" + 
		 		"		 		FROM vehicle_job_session,zone,vehicle\r\n" + 
		 		"		 		WHERE vehicle_job_session.vehicle_id = vehicle.vehicle_id\r\n" + 
		 		"		 		AND vehicle.vehicle_id = zone.vehicle_id \r\n" + 
		 		"		 		AND zone.provider_id = ?\r\n" + 
		 		"		 		ORDER BY vehicle_job_session.date_route DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

//		 if(!queryList.isEmpty()) {
//			 for (ResultMap map : queryList) {
//				 	ProviderLogisticjson model = new ProviderLogisticjson();
//				 	StatusSessionDAO statusSessionDAO = new StatusSessionDAO(); 
//					DriverDAO driverDAO = new DriverDAO(); 
//					VehicleDAO vehicleDAO = new VehicleDAO(); 
//					
//					StatusSessionModel statusSessionModel = statusSessionDAO.FindByID(map.get("status_id") != null ? Integer.parseInt(map.get("status_id")) : Integer.MIN_VALUE);
//					DriverModel driverModel = driverDAO.FindByID(map.get("driver_id") != null ? Integer.parseInt(map.get("driver_id")) : Integer.MIN_VALUE); 
//					VehicleModel vehicleModel = vehicleDAO.FindByID(map.get("vehicle_id") != null ? Integer.parseInt(map.get("vehicle_id")) : Integer.MIN_VALUE); 
//			 }
//		 } 

		 return queryList; 
	 }

}