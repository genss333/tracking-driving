package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.SellerModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.MarketModel;

public class SellerDAO implements DAO<SellerModel>{

	 private Database db;
	 private boolean inherit_model;

	 public SellerDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(SellerModel bean) throws SQLException {

		 String sql = "INSERT INTO seller(`market_id`, `username`, " + 
 					 "`password`, `firstname`, `lastname`, " + 
 					 "`address`, `phone_number`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?)"; 
 
		 return db.add(sql, new String[]{"seller_id"}, bean.getMarketId().getMarketId(), bean.getUsername(),  
 					bean.getPassword(), bean.getFirstname(), bean.getLastname(),  
 					bean.getAddress(), bean.getPhoneNumber()); 
	 } 

	 @Override
	 public int Delete(SellerModel bean) throws SQLException {

		 String sql = "DELETE FROM seller WHERE seller_id = ? "; 

		 return db.remove(sql,bean.getSellerId()); 
	 } 

	 @Override
	 public int Update(SellerModel bean) throws SQLException {

		 String sql = "UPDATE seller SET `market_id` = ?, `username` = ?, " + 
 					 "`password` = ?, `firstname` = ?, `lastname` = ?, " + 
 					 "`address` = ?, `phone_number` = ? WHERE `seller_id` = ?"; 

		 return db.update(sql, bean.getMarketId().getMarketId(), bean.getUsername(),  
 					bean.getPassword(), bean.getFirstname(), bean.getLastname(),  
 					bean.getAddress(), bean.getPhoneNumber(), bean.getSellerId()); 
	 } 

	 @Override
	 public ArrayList<SellerModel> FindAll() throws SQLException {

		 ArrayList<SellerModel> result = new ArrayList<SellerModel>();

		 String sql = "SELECT * FROM seller ORDER BY seller_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public SellerModel FindByID(SellerModel bean) throws SQLException {

		 SellerModel result = new SellerModel();

		 String sql = "SELECT * FROM seller WHERE seller_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getSellerId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public SellerModel FindByID(int id) throws SQLException {

		 SellerModel result = new SellerModel();

		 String sql = "SELECT * FROM seller WHERE seller_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public SellerModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public SellerModel MappingBeans(ResultMap map) throws SQLException {

		SellerModel model = new SellerModel(); 

		 if(inherit_model) {

			MarketDAO marketDAO = new MarketDAO(); 

			MarketModel marketModel = marketDAO.FindByID(map.get("market_id") != null ? Integer.parseInt(map.get("market_id")) : Integer.MIN_VALUE); 
			model.setMarketId(marketModel); 

		 } else {

			MarketModel marketModel = new MarketModel(); 

			marketModel.setMarketId(map.get("market_id") != null ? Integer.parseInt(map.get("market_id")) : Integer.MIN_VALUE); 
			model.setMarketId(marketModel); 

		 }

		model.setSellerId(map.get("seller_id") != null ? Integer.parseInt(map.get("seller_id")) : Integer.MIN_VALUE); 
		model.setUsername(map.get("username")); 
		model.setPassword(map.get("password")); 
		model.setFirstname(map.get("firstname")); 
		model.setLastname(map.get("lastname")); 
		model.setAddress(map.get("address")); 
		model.setPhoneNumber(map.get("phone_number")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
}