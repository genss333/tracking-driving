package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.OwnerModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.ProviderModel;
import misl.spring.model.OrdersModel;

public class OwnerDAO implements DAO<OwnerModel>{

	 private Database db;
	 private boolean inherit_model;

	 public OwnerDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(OwnerModel bean) throws SQLException {

		 String sql = "INSERT INTO owner(`provider_id`, `order_id`, " + 
 					 "`date`) VALUES(?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"own_order_id"}, bean.getProviderId().getProviderId(), bean.getOrderId().getOrderId(),  
 					bean.getDate()); 
	 } 

	 @Override
	 public int Delete(OwnerModel bean) throws SQLException {

		 String sql = "DELETE FROM owner WHERE own_order_id = ? "; 

		 return db.remove(sql,bean.getOwnOrderId()); 
	 } 

	 @Override
	 public int Update(OwnerModel bean) throws SQLException {

		 String sql = "UPDATE owner SET `provider_id` = ?, `order_id` = ?, " + 
 					 "`date` = ? WHERE `own_order_id` = ?"; 

		 return db.update(sql, bean.getProviderId().getProviderId(), bean.getOrderId().getOrderId(),  
 					bean.getDate(), bean.getOwnOrderId()); 
	 } 

	 @Override
	 public ArrayList<OwnerModel> FindAll() throws SQLException {

		 ArrayList<OwnerModel> result = new ArrayList<OwnerModel>();

		 String sql = "SELECT * FROM owner ORDER BY own_order_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public OwnerModel FindByID(OwnerModel bean) throws SQLException {

		 OwnerModel result = new OwnerModel();

		 String sql = "SELECT * FROM owner WHERE own_order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getOwnOrderId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public OwnerModel FindByID(int id) throws SQLException {

		 OwnerModel result = new OwnerModel();

		 String sql = "SELECT * FROM owner WHERE own_order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public OwnerModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public OwnerModel MappingBeans(ResultMap map) throws SQLException {

		OwnerModel model = new OwnerModel(); 

		 if(inherit_model) {

			ProviderDAO providerDAO = new ProviderDAO(); 
			OrdersDAO ordersDAO = new OrdersDAO(); 

			ProviderModel providerModel = providerDAO.FindByID(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

			OrdersModel ordersModel = ordersDAO.FindByID(map.get("order_id") != null ? Integer.parseInt(map.get("order_id")) : Integer.MIN_VALUE); 
			model.setOrderId(ordersModel); 

		 } else {

			ProviderModel providerModel = new ProviderModel(); 
			OrdersModel ordersModel = new OrdersModel(); 

			providerModel.setProviderId(map.get("provider_id") != null ? Integer.parseInt(map.get("provider_id")) : Integer.MIN_VALUE); 
			model.setProviderId(providerModel); 

			ordersModel.setOrderId(map.get("order_id") != null ? Integer.parseInt(map.get("order_id")) : Integer.MIN_VALUE); 
			model.setOrderId(ordersModel); 

		 }

		model.setOwnOrderId(map.get("own_order_id") != null ? Integer.parseInt(map.get("own_order_id")) : Integer.MIN_VALUE); 
		model.setDate(map.get("date")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
	 public OwnerModel FindByorderID(int id) throws SQLException {

		 OwnerModel result = new OwnerModel();

		 String sql = "SELECT * FROM owner WHERE order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public OwnerModel FindByOrderPhonenumber(String phonenumber) throws SQLException {

		 OwnerModel result = new OwnerModel();

		 String sql = "SELECT * FROM owner INNER JOIN orders ON `owner`.order_id = orders.order_id where orders.phone_number = ?  OR orders.recipient_phone_number = ? limit 1"; 
		 ResultMap querySingle = db.querySingle(sql, phonenumber,phonenumber); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public  ArrayList<OwnerModel> FindByOrderByPhonenumber(String date,String phonenumber ) throws SQLException {

		 ArrayList<OwnerModel> result = new ArrayList<OwnerModel>();

		 String sql = "SELECT * FROM owner INNER JOIN orders ON `owner`.order_id = orders.order_id\r\n"
		 		+ "INNER JOIN orders_route ON  orders.order_id = orders_route.order_id\r\n"
		 		+ "where  orders_route.order_route_status_id !=2 AND date = ? AND (orders.phone_number = ?  OR orders.recipient_phone_number = ?) "; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,date,phonenumber,phonenumber); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 
	 public  ArrayList<OwnerModel> FindByOrderByPhonenumberMore(String date,String phonenumber ) throws SQLException {

		 ArrayList<OwnerModel> result = new ArrayList<OwnerModel>();

		 String sql = "SELECT * FROM owner INNER JOIN orders ON `owner`.order_id = orders.order_id\r\n"
		 		+ "INNER JOIN orders_route ON  orders.order_id = orders_route.order_id\r\n"
		 		+ "where  orders_route.order_route_status_id !=2 AND date >= ? AND (orders.phone_number = ?  OR orders.recipient_phone_number = ?) ORDER BY date DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,date,phonenumber,phonenumber); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
	 
	 public ArrayList<OwnerModel> FindAllSameDate() throws SQLException {

		 ArrayList<OwnerModel> result = new ArrayList<OwnerModel>();

		 String sql = "SELECT * FROM owner GROUP BY date,provider_id ORDER BY date"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
}