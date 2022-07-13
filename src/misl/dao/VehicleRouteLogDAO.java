package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.VehicleRouteLogModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.OrdersRouteModel;

public class VehicleRouteLogDAO implements DAO<VehicleRouteLogModel>{

	 private Database db;
	 private boolean inherit_model;

	 public VehicleRouteLogDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(VehicleRouteLogModel bean) throws SQLException {

		 String sql = "INSERT INTO vehicle_route_log(`order_route_id`, `latitude`, " + 
 					 "`longitude`, `datetime_record`) VALUES(?, ?, " + 
 					 "?, ?)"; 

		 System.out.println(sql +"\n"+bean.getOrderRouteId().getOrderRouteId()+" "+CheckNumber.formatNull(bean.getLatitude())+" "+  
 					CheckNumber.formatNull(bean.getLongitude())+" "+ bean.getDatetimeRecord());
		 return db.add(sql, new String[]{"vehicle_route_log_id"}, bean.getOrderRouteId().getOrderRouteId(), CheckNumber.formatNull(bean.getLatitude()),  
 					CheckNumber.formatNull(bean.getLongitude()), bean.getDatetimeRecord()); 
	 } 

	 @Override
	 public int Delete(VehicleRouteLogModel bean) throws SQLException {

		 String sql = "DELETE FROM vehicle_route_log WHERE vehicle_route_log_id = ? "; 

		 return db.remove(sql,bean.getVehicleRouteLogId()); 
	 } 

	 @Override
	 public int Update(VehicleRouteLogModel bean) throws SQLException {

		 String sql = "UPDATE vehicle_route_log SET `order_route_id` = ?, `latitude` = ?, " + 
 					 "`longitude` = ?, `datetime_record` = ? WHERE `vehicle_route_log_id` = ?"; 

		 return db.update(sql, bean.getOrderRouteId().getOrderRouteId(), CheckNumber.formatNull(bean.getLatitude()),  
 					CheckNumber.formatNull(bean.getLongitude()), bean.getDatetimeRecord(), bean.getVehicleRouteLogId()); 
	 } 

	 @Override
	 public ArrayList<VehicleRouteLogModel> FindAll() throws SQLException {

		 ArrayList<VehicleRouteLogModel> result = new ArrayList<VehicleRouteLogModel>();

		 String sql = "SELECT * FROM vehicle_route_log ORDER BY vehicle_route_log_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleRouteLogModel FindByID(VehicleRouteLogModel bean) throws SQLException {

		 VehicleRouteLogModel result = new VehicleRouteLogModel();

		 String sql = "SELECT * FROM vehicle_route_log WHERE vehicle_route_log_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getVehicleRouteLogId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleRouteLogModel FindByID(int id) throws SQLException {

		 VehicleRouteLogModel result = new VehicleRouteLogModel();

		 String sql = "SELECT * FROM vehicle_route_log WHERE vehicle_route_log_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public VehicleRouteLogModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public VehicleRouteLogModel MappingBeans(ResultMap map) throws SQLException {

		VehicleRouteLogModel model = new VehicleRouteLogModel(); 

		 if(inherit_model) {

			OrdersRouteDAO ordersRouteDAO = new OrdersRouteDAO(); 

			OrdersRouteModel ordersRouteModel = ordersRouteDAO.FindByID(map.get("order_route_id") != null ? Integer.parseInt(map.get("order_route_id")) : Integer.MIN_VALUE); 
			model.setOrderRouteId(ordersRouteModel); 

		 } else {

			OrdersRouteModel ordersRouteModel = new OrdersRouteModel(); 

			ordersRouteModel.setOrderRouteId(map.get("order_route_id") != null ? Integer.parseInt(map.get("order_route_id")) : Integer.MIN_VALUE); 
			model.setOrderRouteId(ordersRouteModel); 

		 }

		model.setVehicleRouteLogId(map.get("vehicle_route_log_id") != null ? Integer.parseInt(map.get("vehicle_route_log_id")) : Integer.MIN_VALUE); 
		model.setLatitude(map.get("latitude") != null ? Double.parseDouble(map.get("latitude")) : Double.MIN_VALUE); 
		model.setLongitude(map.get("longitude") != null ? Double.parseDouble(map.get("longitude")) : Double.MIN_VALUE); 
		model.setDatetimeRecord(map.get("datetime_record")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 }
	 public ArrayList<VehicleRouteLogModel> FindAlllog(String start, String end, int id) throws SQLException {

		 ArrayList<VehicleRouteLogModel> result = new ArrayList<VehicleRouteLogModel>();

		 String sql = "SELECT log.*\n" + 
		 		"FROM vehicle_route_log log, vehicle_job_session job, orders_route orr\n" + 
		 		"WHERE log.order_route_id = orr.order_route_id\n" + 
		 		"AND orr.vehicle_route_id = job.Vehicle_Job_Session_id\n" +  
		 		"AND log.datetime_record >= ?" + 
		 		"AND log.datetime_record <= ?"+
		 		"AND orr.vehicle_route_id = ? ORDER BY log.datetime_record";
//		 System.out.println(sql);
//		 System.out.println(start +" "+end+" "+id);
		 ArrayList<ResultMap> queryList = db.queryList(sql, start, end, id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	
	 
	 
	 public VehicleRouteLogModel FindAllByOrderRouteId(int orderRouteId) throws SQLException {

		 VehicleRouteLogModel result = new VehicleRouteLogModel();

		 String sql = "SELECT * FROM vehicle_route_log WHERE order_route_id = ? ORDER BY vehicle_route_log_id DESC LIMIT 1";
		 ResultMap querySingle = db.querySingle(sql, orderRouteId); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 
	 public ArrayList<VehicleRouteLogModel> FindAllByJobSection(int id) throws SQLException {

		 ArrayList<VehicleRouteLogModel> result = new ArrayList<VehicleRouteLogModel>();

		 String sql = "SELECT log.* \r\n"
		 		+ "FROM vehicle_route_log log, vehicle_job_session job, orders_route orr\r\n"
		 		+ "WHERE log.order_route_id = orr.order_route_id\r\n"
		 		+ "AND orr.vehicle_route_id = job.Vehicle_Job_Session_id \r\n"
		 		+ "AND orr.vehicle_route_id = ? ORDER BY log.datetime_record DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public VehicleRouteLogModel FindAllByJobSectionAndOrderRoute(int OrderRouteId) throws SQLException {

		 VehicleRouteLogModel result = new VehicleRouteLogModel();

		 String sql = "SELECT log.* FROM vehicle_route_log log INNER JOIN orders_route orr ON log.order_route_id = orr.order_route_id \r\n"
		 		+ "WHERE  orr.vehicle_route_id = ? ORDER BY log.datetime_record DESC limit 1"; 
	
		 ResultMap querySingle = db.querySingle(sql,OrderRouteId); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 
}