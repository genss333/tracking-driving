package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.DriverModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class DriverDAO implements DAO<DriverModel>{

	 private Database db;
	 private boolean inherit_model;

	 public DriverDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(DriverModel bean) throws SQLException {

		 String sql = "INSERT INTO driver(`username`, `password`, " + 
 					 "`firstname`, `lastname`, `address`, " + 
 					 "`birthday`, `phonenumber`, `img`, " + 
 					 "`driver_device`, `netpackage`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?, ?)"; 
 
		 return db.add(sql, new String[]{"driver_id"}, bean.getUsername(), bean.getPassword(),  
 					 bean.getFirstname(), bean.getLastname(), bean.getAddress(),  
 					 bean.getBirthday(), bean.getPhonenumber(), bean.getImg(),  
 					 bean.getDriverDevice(), bean.getNetpackage()); 
	 } 

	 @Override
	 public int Delete(DriverModel bean) throws SQLException {

		 String sql = "DELETE FROM driver WHERE driver_id = ? "; 

		 return db.remove(sql,bean.getDriverId()); 
	 } 

	 @Override
	 public int Update(DriverModel bean) throws SQLException {

		 String sql = "UPDATE driver SET `username` = ?, `password` = ?, " + 
 					 "`firstname` = ?, `lastname` = ?, `address` = ?, " + 
 					 "`birthday` = ?, `phonenumber` = ?, `img` = ?, " + 
 					 "`driver_device` = ?, `netpackage` = ? WHERE `driver_id` = ?"; 

		 return db.update(sql, bean.getUsername(), bean.getPassword(),  
 					 bean.getFirstname(), bean.getLastname(), bean.getAddress(),  
 					 bean.getBirthday(), bean.getPhonenumber(), bean.getImg(),  
 					 bean.getDriverDevice(), bean.getNetpackage(), bean.getDriverId()); 
	 } 

	 @Override
	 public ArrayList<DriverModel> FindAll() throws SQLException {

		 ArrayList<DriverModel> result = new ArrayList<DriverModel>();

		 String sql = "SELECT * FROM driver ORDER BY driver_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public DriverModel FindByID(DriverModel bean) throws SQLException {

		 DriverModel result = new DriverModel();

		 String sql = "SELECT * FROM driver WHERE driver_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getDriverId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public DriverModel FindByID(int id) throws SQLException {

		 DriverModel result = new DriverModel();

		 String sql = "SELECT * FROM driver WHERE driver_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public DriverModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public DriverModel MappingBeans(ResultMap map) throws SQLException {

		DriverModel model = new DriverModel(); 

		model.setDriverId(map.get("driver_id") != null ? Integer.parseInt(map.get("driver_id")) : Integer.MIN_VALUE); 
		model.setUsername(map.get("username")); 
		model.setPassword(map.get("password")); 
		model.setFirstname(map.get("firstname")); 
		model.setLastname(map.get("lastname")); 
		model.setAddress(map.get("address")); 
		model.setBirthday(map.get("birthday")); 
		model.setPhonenumber(map.get("phonenumber")); 
		model.setImg(map.get("img")); 
		model.setDriverDevice(map.get("driver_device")); 
		model.setNetpackage(map.get("netpackage")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
	 public DriverModel FindByUsername(String username) throws SQLException {

		 DriverModel result = new DriverModel();

		 String sql = "SELECT * FROM driver WHERE username = ?";
		 ResultMap querySingle = db.querySingle(sql, username); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 }
}