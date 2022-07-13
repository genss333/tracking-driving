package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.UsersModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class UsersDAO implements DAO<UsersModel>{

	 private Database db;
	 private boolean inherit_model;

	 public UsersDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(UsersModel bean) throws SQLException {

		 String sql = "INSERT INTO users(`firstname`, `lastname`, " + 
 					 "`email`, `password`, `roleId`) VALUES(?, ?, " + 
 					 "?, ?, ?)"; 
 
		 return db.add(sql, new String[]{"userId"}, bean.getFirstname(), bean.getLastname(),  
 					 bean.getEmail(), bean.getPassword(), CheckNumber.formatNull(bean.getRoleid())); 
	 } 

	 @Override
	 public int Delete(UsersModel bean) throws SQLException {

		 String sql = "DELETE FROM users WHERE userId = ? "; 

		 return db.remove(sql,bean.getUserid()); 
	 } 

	 @Override
	 public int Update(UsersModel bean) throws SQLException {

		 String sql = "UPDATE users SET `firstname` = ?, `lastname` = ?, " + 
 					 "`email` = ?, `password` = ?, `roleId` = ? WHERE `userId` = ?"; 

		 return db.update(sql, bean.getFirstname(), bean.getLastname(),  
 					 bean.getEmail(), bean.getPassword(), CheckNumber.formatNull(bean.getRoleid()), bean.getUserid()); 
	 } 

	 @Override
	 public ArrayList<UsersModel> FindAll() throws SQLException {

		 ArrayList<UsersModel> result = new ArrayList<UsersModel>();

		 String sql = "SELECT * FROM users ORDER BY userId DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersModel FindByID(UsersModel bean) throws SQLException {

		 UsersModel result = new UsersModel();

		 String sql = "SELECT * FROM users WHERE userId = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getUserid()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersModel FindByID(int id) throws SQLException {

		 UsersModel result = new UsersModel();

		 String sql = "SELECT * FROM users WHERE userId = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public UsersModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public UsersModel MappingBeans(ResultMap map) throws SQLException {

		UsersModel model = new UsersModel(); 

		model.setUserid(map.get("userId") != null ? Integer.parseInt(map.get("userId")) : Integer.MIN_VALUE); 
		model.setFirstname(map.get("firstname")); 
		model.setLastname(map.get("lastname")); 
		model.setEmail(map.get("email")); 
		model.setPassword(map.get("password")); 
		model.setRoleid(map.get("roleId") != null ? Integer.parseInt(map.get("roleId")) : Integer.MIN_VALUE); 

		return model; 
	 } 
}