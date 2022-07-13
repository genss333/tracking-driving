package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.OrdersRouteModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.OrdersModel;
import misl.spring.model.VehicleJobSessionModel;
import misl.spring.model.OrderRouteStatusModel;

public class OrdersRouteDAO implements DAO<OrdersRouteModel>{

	 private Database db;
	 private boolean inherit_model;

	 public OrdersRouteDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(OrdersRouteModel bean) throws SQLException {

		 String sql = "INSERT INTO orders_route(`order_id`, `vehicle_route_id`, " + 
 					 "`index`, `distance`, `duration`, " + 
 					 "`order_route_status_id`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"order_route_id"}, bean.getOrderId().getOrderId(), bean.getVehicleRouteId().getVehicleJobSessionId(),  
 					CheckNumber.formatNull(bean.getIndex()), CheckNumber.formatNull(bean.getDistance()), CheckNumber.formatNull(bean.getDuration()),  
 					bean.getOrderRouteStatusId().getOrderRouteStatusId()); 
	 } 

	 @Override
	 public int Delete(OrdersRouteModel bean) throws SQLException {

		 String sql = "DELETE FROM orders_route WHERE order_route_id = ? "; 

		 return db.remove(sql,bean.getOrderRouteId()); 
	 } 

	 @Override
	 public int Update(OrdersRouteModel bean) throws SQLException {

		 String sql = "UPDATE orders_route SET `order_id` = ?, `vehicle_route_id` = ?, " + 
 					 "`index` = ?, `distance` = ?, `duration` = ?, " + 
 					 "`order_route_status_id` = ? WHERE `order_route_id` = ?"; 

		 return db.update(sql, bean.getOrderId().getOrderId(), bean.getVehicleRouteId().getVehicleJobSessionId(),  
 					CheckNumber.formatNull(bean.getIndex()), CheckNumber.formatNull(bean.getDistance()), CheckNumber.formatNull(bean.getDuration()),  
 					bean.getOrderRouteStatusId().getOrderRouteStatusId(), bean.getOrderRouteId()); 
	 } 

	 @Override
	 public ArrayList<OrdersRouteModel> FindAll() throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route ORDER BY order_route_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersRouteModel FindByID(OrdersRouteModel bean) throws SQLException {

		 OrdersRouteModel result = new OrdersRouteModel();

		 String sql = "SELECT * FROM orders_route WHERE order_route_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getOrderRouteId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersRouteModel FindByID(int id) throws SQLException {

		 OrdersRouteModel result = new OrdersRouteModel();

		 String sql = "SELECT * FROM orders_route WHERE order_route_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersRouteModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public OrdersRouteModel MappingBeans(ResultMap map) throws SQLException {

		OrdersRouteModel model = new OrdersRouteModel(); 

		 if(inherit_model) {

			OrdersDAO ordersDAO = new OrdersDAO(); 
			VehicleJobSessionDAO vehicleJobSessionDAO = new VehicleJobSessionDAO(); 
			OrderRouteStatusDAO orderRouteStatusDAO = new OrderRouteStatusDAO(); 

			OrdersModel ordersModel = ordersDAO.FindByID(map.get("order_id") != null ? Integer.parseInt(map.get("order_id")) : Integer.MIN_VALUE); 
			model.setOrderId(ordersModel); 

			VehicleJobSessionModel vehicleJobSessionModel = vehicleJobSessionDAO.FindByID(map.get("vehicle_route_id") != null ? Integer.parseInt(map.get("vehicle_route_id")) : Integer.MIN_VALUE); 
			model.setVehicleRouteId(vehicleJobSessionModel); 

			OrderRouteStatusModel orderRouteStatusModel = orderRouteStatusDAO.FindByID(map.get("order_route_status_id") != null ? Integer.parseInt(map.get("order_route_status_id")) : Integer.MIN_VALUE); 
			model.setOrderRouteStatusId(orderRouteStatusModel); 

		 } else {

			OrdersModel ordersModel = new OrdersModel(); 
			VehicleJobSessionModel vehicleJobSessionModel = new VehicleJobSessionModel(); 
			OrderRouteStatusModel orderRouteStatusModel = new OrderRouteStatusModel(); 

			ordersModel.setOrderId(map.get("order_id") != null ? Integer.parseInt(map.get("order_id")) : Integer.MIN_VALUE); 
			model.setOrderId(ordersModel); 

			vehicleJobSessionModel.setVehicleJobSessionId(map.get("vehicle_route_id") != null ? Integer.parseInt(map.get("vehicle_route_id")) : Integer.MIN_VALUE); 
			model.setVehicleRouteId(vehicleJobSessionModel); 

			orderRouteStatusModel.setOrderRouteStatusId(map.get("order_route_status_id") != null ? Integer.parseInt(map.get("order_route_status_id")) : Integer.MIN_VALUE); 
			model.setOrderRouteStatusId(orderRouteStatusModel); 

		 }

		model.setOrderRouteId(map.get("order_route_id") != null ? Integer.parseInt(map.get("order_route_id")) : Integer.MIN_VALUE); 
		model.setIndex(map.get("index") != null ? Integer.parseInt(map.get("index")) : Integer.MIN_VALUE); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 
		model.setDistance(map.get("distance") != null ? Double.parseDouble(map.get("distance")) : Double.MIN_VALUE); 
		model.setDuration(map.get("duration") != null ? Double.parseDouble(map.get("duration")) : Double.MIN_VALUE); 

		return model; 
	 } 
	 public ArrayList<OrdersRouteModel> FindOrder() throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT orr.* \n" + 
		 		"FROM orders_route orr, vehicle_job_session js, orders o\n" + 
		 		"WHERE orr.vehicle_route_id = js.Vehicle_Job_Session_id\n" + 
		 		"AND orr.order_id = o.order_id\n" + 
		 		"AND js.status_id = 3\n"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<OrdersRouteModel> FindOrder(int jobId, int status) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT orr.*\r\n" + 
		 		"FROM orders_route orr, vehicle_job_session js\r\n" + 
		 		"WHERE orr.vehicle_route_id = js.Vehicle_Job_Session_id\r\n" + 
		 		"AND orr.order_route_status_id = ?\r\n" + 
		 		"AND orr.vehicle_route_id = ?\r\n" + 
		 		"ORDER BY orr.`index`\r\n" + 
		 		""; 
		 ArrayList<ResultMap> queryList = db.queryList(sql, status, jobId); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<OrdersRouteModel> FindByJobSessionId(int id) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route WHERE vehicle_route_id = ? ORDER BY orders_route.index"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<OrdersRouteModel> FindByJobSessionIdWhereStatus(int id) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route WHERE vehicle_route_id = ? AND order_route_status_id = 1 ORDER BY orders_route.index"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<OrdersRouteModel> FindAllByOrderID(int id) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route where order_id = ? ORDER BY order_route_id ASC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public OrdersRouteModel FindJobSecsionWithOrderRouteByOrderId(int id) throws SQLException {

		 OrdersRouteModel result = new OrdersRouteModel();

		 String sql = "SELECT * FROM orders_route INNER JOIN vehicle_job_session ON  orders_route.vehicle_route_id = vehicle_job_session.Vehicle_Job_Session_id WHERE order_id = ? AND status_id = 2 ORDER BY order_route_id ASC"; 
		
		 ResultMap querySingle = db.querySingle(sql,id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 


		 return result; 
	 } 
	 public ArrayList<OrdersRouteModel> FindAllByjobsessionID(int id) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route where vehicle_route_id = ? ORDER BY order_route_id ASC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public ArrayList<OrdersRouteModel> FindAllbysessionId(int id) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route where vehicle_route_id = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 public OrdersRouteModel FindByOrderId(int order_id) throws SQLException {

		 OrdersRouteModel result = new OrdersRouteModel();

		 String sql = "SELECT * FROM orders_route WHERE order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, order_id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public OrdersRouteModel FindByOrderID(int id) throws SQLException {

		 OrdersRouteModel result = new OrdersRouteModel();

		 String sql = "SELECT * FROM orders_route WHERE order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public ArrayList<OrdersRouteModel> FindAllByjobsessionIdBetween(int id,int index,int index2) throws SQLException {

		 ArrayList<OrdersRouteModel> result = new ArrayList<OrdersRouteModel>();

		 String sql = "SELECT * FROM orders_route WHERE vehicle_route_id = ? and  order_route_status_id = 1 and `index`< ?  and `index`!= ? ORDER BY orders_route.index"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id,index,index2); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 
}