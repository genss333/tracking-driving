package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.UsersLocationModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.UsersModel;

public class UsersLocationDAO implements DAO<UsersLocationModel>{

	 private Database db;
	 private boolean inherit_model;

	 public UsersLocationDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(UsersLocationModel bean) throws SQLException {

		 String sql = "INSERT INTO users_Location(`address`, `PROVINCE`, " + 
 					 "`AMPHUR`, `TAMBON`, `ZIPCODE`, " + 
 					 "`userId`, `lat`, `lng`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?)"; 
 
		 return db.add(sql, new String[]{"userLocationId"}, bean.getAddress(), bean.getProvince(),  
 					bean.getAmphur(), bean.getTambon(), CheckNumber.formatNull(bean.getZipcode()),  
 					bean.getUserid().getUserid(), bean.getLat(), bean.getLng()); 
	 } 

	 @Override
	 public int Delete(UsersLocationModel bean) throws SQLException {

		 String sql = "DELETE FROM users_Location WHERE userLocationId = ? "; 

		 return db.remove(sql,bean.getUserlocationid()); 
	 } 

	 @Override
	 public int Update(UsersLocationModel bean) throws SQLException {

		 String sql = "UPDATE users_Location SET `address` = ?, `PROVINCE` = ?, " + 
 					 "`AMPHUR` = ?, `TAMBON` = ?, `ZIPCODE` = ?, " + 
 					 "`userId` = ?, `lat` = ?, `lng` = ? WHERE `userLocationId` = ?"; 

		 return db.update(sql, bean.getAddress(), bean.getProvince(),  
 					bean.getAmphur(), bean.getTambon(), CheckNumber.formatNull(bean.getZipcode()),  
 					bean.getUserid().getUserid(), bean.getLat(), bean.getLng(), bean.getUserlocationid()); 
	 } 

	 @Override
	 public ArrayList<UsersLocationModel> FindAll() throws SQLException {

		 ArrayList<UsersLocationModel> result = new ArrayList<UsersLocationModel>();

		 String sql = "SELECT * FROM users_Location ORDER BY userLocationId DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersLocationModel FindByID(UsersLocationModel bean) throws SQLException {

		 UsersLocationModel result = new UsersLocationModel();

		 String sql = "SELECT * FROM users_Location WHERE userLocationId = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getUserlocationid()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersLocationModel FindByID(int id) throws SQLException {

		 UsersLocationModel result = new UsersLocationModel();

		 String sql = "SELECT * FROM users_Location WHERE userLocationId = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersLocationModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public UsersLocationModel MappingBeans(ResultMap map) throws SQLException {

		UsersLocationModel model = new UsersLocationModel(); 

		 if(inherit_model) {

			UsersDAO usersDAO = new UsersDAO(); 

			UsersModel usersModel = usersDAO.FindByID(map.get("userId") != null ? Integer.parseInt(map.get("userId")) : Integer.MIN_VALUE); 
			model.setUserid(usersModel); 

		 } else {

			UsersModel usersModel = new UsersModel(); 

			usersModel.setUserid(map.get("userId") != null ? Integer.parseInt(map.get("userId")) : Integer.MIN_VALUE); 
			model.setUserid(usersModel); 

		 }

		model.setUserlocationid(map.get("userLocationId") != null ? Integer.parseInt(map.get("userLocationId")) : Integer.MIN_VALUE); 
		model.setAddress(map.get("address")); 
		model.setProvince(map.get("PROVINCE")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setTambon(map.get("TAMBON")); 
		model.setZipcode(map.get("ZIPCODE") != null ? Integer.parseInt(map.get("ZIPCODE")) : Integer.MIN_VALUE); 
		model.setLat(map.get("lat")); 
		model.setLng(map.get("lng")); 

		return model; 
	 } 
	 public UsersLocationModel FindByUserID(int id) throws SQLException {

		 UsersLocationModel result = new UsersLocationModel();

		 String sql = "SELECT * FROM users_Location WHERE userid = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 
	 public ArrayList<UsersLocationModel> FindAllByUserId(int id) throws SQLException {

		 ArrayList<UsersLocationModel> result = new ArrayList<UsersLocationModel>();

		 String sql = "SELECT * FROM users_Location WHERE userid = ?"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,id); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 
}