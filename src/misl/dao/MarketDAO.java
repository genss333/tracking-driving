package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.MarketModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class MarketDAO implements DAO<MarketModel>{

	 private Database db;
	 private boolean inherit_model;

	 public MarketDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(MarketModel bean) throws SQLException {

		 String sql = "INSERT INTO market(`name`, `latitude`, " + 
 					 "`longitude`, `address`, `TAMBON`, " + 
 					 "`AMPHUR`, `province`, `phone_number`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?)"; 
 
		 return db.add(sql, new String[]{"market_id"}, bean.getName(), CheckNumber.formatNull(bean.getLatitude()),  
 					 CheckNumber.formatNull(bean.getLongitude()), bean.getAddress(), bean.getTambon(),  
 					 bean.getAmphur(), bean.getProvince(), bean.getPhoneNumber()); 
	 } 

	 @Override
	 public int Delete(MarketModel bean) throws SQLException {

		 String sql = "DELETE FROM market WHERE market_id = ? "; 

		 return db.remove(sql,bean.getMarketId()); 
	 } 

	 @Override
	 public int Update(MarketModel bean) throws SQLException {

		 String sql = "UPDATE market SET `name` = ?, `latitude` = ?, " + 
 					 "`longitude` = ?, `address` = ?, `TAMBON` = ?, " + 
 					 "`AMPHUR` = ?, `province` = ?, `phone_number` = ? WHERE `market_id` = ?"; 

		 return db.update(sql, bean.getName(), CheckNumber.formatNull(bean.getLatitude()),  
 					 CheckNumber.formatNull(bean.getLongitude()), bean.getAddress(), bean.getTambon(),  
 					 bean.getAmphur(), bean.getProvince(), bean.getPhoneNumber(), bean.getMarketId()); 
	 } 

	 @Override
	 public ArrayList<MarketModel> FindAll() throws SQLException {

		 ArrayList<MarketModel> result = new ArrayList<MarketModel>();

		 String sql = "SELECT * FROM market ORDER BY market_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public MarketModel FindByID(MarketModel bean) throws SQLException {

		 MarketModel result = new MarketModel();

		 String sql = "SELECT * FROM market WHERE market_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getMarketId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public MarketModel FindByID(int id) throws SQLException {

		 MarketModel result = new MarketModel();

		 String sql = "SELECT * FROM market WHERE market_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public MarketModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public MarketModel MappingBeans(ResultMap map) throws SQLException {

		MarketModel model = new MarketModel(); 

		model.setMarketId(map.get("market_id") != null ? Integer.parseInt(map.get("market_id")) : Integer.MIN_VALUE); 
		model.setName(map.get("name")); 
		model.setLatitude(map.get("latitude") != null ? Double.parseDouble(map.get("latitude")) : Double.MIN_VALUE); 
		model.setLongitude(map.get("longitude") != null ? Double.parseDouble(map.get("longitude")) : Double.MIN_VALUE); 
		model.setAddress(map.get("address")); 
		model.setTambon(map.get("TAMBON")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setProvince(map.get("province")); 
		model.setPhoneNumber(map.get("phone_number")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
}