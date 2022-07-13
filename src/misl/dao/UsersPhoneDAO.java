package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.UsersPhoneModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 
import misl.spring.model.UsersModel;

public class UsersPhoneDAO implements DAO<UsersPhoneModel>{

	 private Database db;
	 private boolean inherit_model;

	 public UsersPhoneDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(UsersPhoneModel bean) throws SQLException {

		 String sql = "INSERT INTO users_phone(`phone_number`, `userId`) VALUES(?, ?)"; 
 
		 return db.add(sql, new String[]{"userphoneId"}, bean.getPhoneNumber(), bean.getUserid().getUserid()); 
	 } 

	 @Override
	 public int Delete(UsersPhoneModel bean) throws SQLException {

		 String sql = "DELETE FROM users_phone WHERE userphoneId = ? "; 

		 return db.remove(sql,bean.getUserphoneid()); 
	 } 

	 @Override
	 public int Update(UsersPhoneModel bean) throws SQLException {

		 String sql = "UPDATE users_phone SET `phone_number` = ?, `userId` = ? WHERE `userphoneId` = ?"; 

		 return db.update(sql, bean.getPhoneNumber(), bean.getUserid().getUserid(), bean.getUserphoneid()); 
	 } 

	 @Override
	 public ArrayList<UsersPhoneModel> FindAll() throws SQLException {

		 ArrayList<UsersPhoneModel> result = new ArrayList<UsersPhoneModel>();

		 String sql = "SELECT * FROM users_phone ORDER BY userphoneId DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersPhoneModel FindByID(UsersPhoneModel bean) throws SQLException {

		 UsersPhoneModel result = new UsersPhoneModel();

		 String sql = "SELECT * FROM users_phone WHERE userphoneId = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getUserphoneid()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersPhoneModel FindByID(int id) throws SQLException {

		 UsersPhoneModel result = new UsersPhoneModel();

		 String sql = "SELECT * FROM users_phone WHERE userphoneId = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersPhoneModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public UsersPhoneModel MappingBeans(ResultMap map) throws SQLException {

		UsersPhoneModel model = new UsersPhoneModel(); 

		 if(inherit_model) {

			UsersDAO usersDAO = new UsersDAO(); 

			UsersModel usersModel = usersDAO.FindByID(map.get("userId") != null ? Integer.parseInt(map.get("userId")) : Integer.MIN_VALUE); 
			model.setUserid(usersModel); 

		 } else {

			UsersModel usersModel = new UsersModel(); 

			usersModel.setUserid(map.get("userId") != null ? Integer.parseInt(map.get("userId")) : Integer.MIN_VALUE); 
			model.setUserid(usersModel); 

		 }

		model.setUserphoneid(map.get("userphoneId") != null ? Integer.parseInt(map.get("userphoneId")) : Integer.MIN_VALUE); 
		model.setPhoneNumber(map.get("phone_number")); 

		return model; 
	 }
	 public UsersPhoneModel FindByPhoneNumber(String phoneNumber) throws SQLException {

		 UsersPhoneModel result = new UsersPhoneModel();

		 String sql = "SELECT * FROM users_phone WHERE phone_number = ?";
		 ResultMap querySingle = db.querySingle(sql, phoneNumber); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 }
}