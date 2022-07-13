package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.OrdersModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.SellerModel;

public class OrdersDAO implements DAO<OrdersModel>{

	 private Database db;
	 private boolean inherit_model;

	 public OrdersDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(OrdersModel bean) throws SQLException {

		 String sql = "INSERT INTO orders(`seller_id`, `order_number`, " + 
 					 "`phone_number`, `firstname`, `lastname`, " + 
 					 "`recipient_phone_number`, `recipient_lastname`, `recipient_firstname`, " + 
 					 "`address`, `TAMBON`, `AMPHUR`, " + 
 					 "`province`, `zipcode`, `latitude`, " + 
 					 "`longitude`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"order_id"}, bean.getSellerId().getSellerId(), bean.getOrderNumber(),  
 					bean.getPhoneNumber(), bean.getFirstname(), bean.getLastname(),  
 					bean.getRecipientPhoneNumber(), bean.getRecipientLastname(), bean.getRecipientFirstname(),  
 					bean.getAddress(), bean.getTambon(), bean.getAmphur(),  
 					bean.getProvince(), bean.getZipcode(), CheckNumber.formatNull(bean.getLatitude()),  
 					CheckNumber.formatNull(bean.getLongitude())); 
	 } 

	 @Override
	 public int Delete(OrdersModel bean) throws SQLException {

		 String sql = "DELETE FROM orders WHERE order_id = ? "; 

		 return db.remove(sql,bean.getOrderId()); 
	 } 

	 @Override
	 public int Update(OrdersModel bean) throws SQLException {

		 String sql = "UPDATE orders SET `seller_id` = ?, `order_number` = ?, " + 
 					 "`phone_number` = ?, `firstname` = ?, `lastname` = ?, " + 
 					 "`recipient_phone_number` = ?, `recipient_lastname` = ?, `recipient_firstname` = ?, " + 
 					 "`address` = ?, `TAMBON` = ?, `AMPHUR` = ?, " + 
 					 "`province` = ?, `zipcode` = ?, `latitude` = ?, " + 
 					 "`longitude` = ? WHERE `order_id` = ?"; 

		 return db.update(sql, bean.getSellerId().getSellerId(), bean.getOrderNumber(),  
 					bean.getPhoneNumber(), bean.getFirstname(), bean.getLastname(),  
 					bean.getRecipientPhoneNumber(), bean.getRecipientLastname(), bean.getRecipientFirstname(),  
 					bean.getAddress(), bean.getTambon(), bean.getAmphur(),  
 					bean.getProvince(), bean.getZipcode(), CheckNumber.formatNull(bean.getLatitude()),  
 					CheckNumber.formatNull(bean.getLongitude()), bean.getOrderId()); 
	 } 

	 @Override
	 public ArrayList<OrdersModel> FindAll() throws SQLException {

		 ArrayList<OrdersModel> result = new ArrayList<OrdersModel>();

		 String sql = "SELECT * FROM orders ORDER BY order_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersModel FindByID(OrdersModel bean) throws SQLException {

		 OrdersModel result = new OrdersModel();

		 String sql = "SELECT * FROM orders WHERE order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getOrderId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersModel FindByID(int id) throws SQLException {

		 OrdersModel result = new OrdersModel();

		 String sql = "SELECT * FROM orders WHERE order_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public OrdersModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public OrdersModel MappingBeans(ResultMap map) throws SQLException {

		OrdersModel model = new OrdersModel(); 

		 if(inherit_model) {

			SellerDAO sellerDAO = new SellerDAO(); 

			SellerModel sellerModel = sellerDAO.FindByID(map.get("seller_id") != null ? Integer.parseInt(map.get("seller_id")) : Integer.MIN_VALUE); 
			model.setSellerId(sellerModel); 

		 } else {

			SellerModel sellerModel = new SellerModel(); 

			sellerModel.setSellerId(map.get("seller_id") != null ? Integer.parseInt(map.get("seller_id")) : Integer.MIN_VALUE); 
			model.setSellerId(sellerModel); 

		 }

		model.setOrderId(map.get("order_id") != null ? Integer.parseInt(map.get("order_id")) : Integer.MIN_VALUE); 
		model.setOrderNumber(map.get("order_number")); 
		model.setPhoneNumber(map.get("phone_number")); 
		model.setFirstname(map.get("firstname")); 
		model.setLastname(map.get("lastname")); 
		model.setRecipientPhoneNumber(map.get("recipient_phone_number")); 
		model.setRecipientLastname(map.get("recipient_lastname")); 
		model.setRecipientFirstname(map.get("recipient_firstname")); 
		model.setAddress(map.get("address")); 
		model.setTambon(map.get("TAMBON")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setProvince(map.get("province")); 
		model.setZipcode(map.get("zipcode")); 
		model.setLatitude(map.get("latitude") != null ? Double.parseDouble(map.get("latitude")) : Double.MIN_VALUE); 
		model.setLongitude(map.get("longitude") != null ? Double.parseDouble(map.get("longitude")) : Double.MIN_VALUE); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
}