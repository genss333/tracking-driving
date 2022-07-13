package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.StatusSessionModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class StatusSessionDAO implements DAO<StatusSessionModel>{

	 private Database db;
	 private boolean inherit_model;

	 public StatusSessionDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(StatusSessionModel bean) throws SQLException {

		 String sql = "INSERT INTO status_session(`status_name`) VALUES(?)"; 
 
		 return db.add(sql, new String[]{"status_session_id"}, bean.getStatusName()); 
	 } 

	 @Override
	 public int Delete(StatusSessionModel bean) throws SQLException {

		 String sql = "DELETE FROM status_session WHERE status_session_id = ? "; 

		 return db.remove(sql,bean.getStatusSessionId()); 
	 } 

	 @Override
	 public int Update(StatusSessionModel bean) throws SQLException {

		 String sql = "UPDATE status_session SET `status_name` = ? WHERE `status_session_id` = ?"; 

		 return db.update(sql, bean.getStatusName(), bean.getStatusSessionId()); 
	 } 

	 @Override
	 public ArrayList<StatusSessionModel> FindAll() throws SQLException {

		 ArrayList<StatusSessionModel> result = new ArrayList<StatusSessionModel>();

		 String sql = "SELECT * FROM status_session ORDER BY status_session_id DESC"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public StatusSessionModel FindByID(StatusSessionModel bean) throws SQLException {

		 StatusSessionModel result = new StatusSessionModel();

		 String sql = "SELECT * FROM status_session WHERE status_session_id = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getStatusSessionId()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public StatusSessionModel FindByID(int id) throws SQLException {

		 StatusSessionModel result = new StatusSessionModel();

		 String sql = "SELECT * FROM status_session WHERE status_session_id = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public StatusSessionModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public StatusSessionModel MappingBeans(ResultMap map) throws SQLException {

		StatusSessionModel model = new StatusSessionModel(); 

		model.setStatusSessionId(map.get("status_session_id") != null ? Integer.parseInt(map.get("status_session_id")) : Integer.MIN_VALUE); 
		model.setStatusName(map.get("status_name")); 
		model.setCreatedAt(map.get("created_at")); 
		model.setUpdatedAt(map.get("updated_at")); 

		return model; 
	 } 
}