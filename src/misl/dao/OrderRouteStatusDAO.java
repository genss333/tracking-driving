package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.OrderRouteStatusModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class OrderRouteStatusDAO implements DAO<OrderRouteStatusModel>{

	 private Database db;
	 private boolean inherit_model;

	 public OrderRouteStatusDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(OrderRouteStatusModel bean) throws SQLException {

		 String sql = "INSERT INTO order_route_status(`status_name`) VALUES(?)"; 
 
		 return db.add(sql, new String[]{"order_route_status_id"}, bean.getStatusName()); 
	 } 

	 @Override
	 public int Delete(OrderRouteStatusModel bean) throws SQLException {

		 String sql = "DELETE FROM order_route_status WHERE order_route_status_id = ? "; 

		 return db.remove(sql,bean.getOrderRouteStatusId()); 
	 } 

	 @Override
	 public int Update(OrderRouteStatusModel bean) throws SQLException {

		 String sql = "UPDATE order_route_status SET `status_name` = ? WHERE `order_route_status_id` = ?"; 

		 return db.update(sql, bean.getStatusName(), bean.getOrderRouteStatusId()); 
	 } 

	 @Override
	 public ArrayList<OrderRouteStatusModel> FindAll() throws SQLException {

		 ArrayList<OrderRouteStatusModel> result = new ArrayList<OrderRouteStatusModel>();

		 String sql = "SELECT * FROM order_route_status ORDER BY order_route_status_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public OrderRouteStatusModel FindByID(OrderRouteStatusModel bean) throws SQLException {

		 OrderRouteStatusModel result = new OrderRouteStatusModel();

		 String sql = "SELECT * FROM order_route_status WHERE order_route_status_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getOrderRouteStatusId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public OrderRouteStatusModel FindByID(int id) throws SQLException {

		 OrderRouteStatusModel result = new OrderRouteStatusModel();

		 String sql = "SELECT * FROM order_route_status WHERE order_route_status_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public OrderRouteStatusModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public OrderRouteStatusModel MappingBeans(ResultMap map) throws SQLException {

		OrderRouteStatusModel model = new OrderRouteStatusModel(); 

		model.setOrderRouteStatusId(map.get("order_route_status_id") != null ? Integer.parseInt(map.get("order_route_status_id")) : Integer.MIN_VALUE); 
		model.setStatusName(map.get("status_name")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
}