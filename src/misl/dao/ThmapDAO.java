package misl.dao;

import java.util.ArrayList;
import java.sql.SQLException; 

import misl.dao.impl.DAO;
import misl.db.Database;
import misl.spring.model.ThmapModel;
import misl.spring.model.extra.ResultMap;
import misl.util.CheckNumber; 

public class ThmapDAO implements DAO<ThmapModel>{

	 private Database db;
	 private boolean inherit_model;

	 public ThmapDAO() {
		 db = new Database();
		  inherit_model = true;
	 }

	 public void setInheritModel(boolean value) {
		 inherit_model = value;
	 }


	 @Override
	 public int Add(ThmapModel bean) throws SQLException {

		 String sql = "INSERT INTO thmap(`PROVINCE`, `AMPHUR`, " + 
 					 "`TAMBON`, `lat`, `lng`, " + 
 					 "`zipcode`) VALUES(?, ?, " + 
 					 "?, ?, ?, " + 
 					 "?)"; 
 
		 return db.add(sql, new String[]{"THmapId"}, bean.getProvince(), bean.getAmphur(),  
 					 bean.getTambon(), bean.getLat(), bean.getLng(),  
 					 CheckNumber.formatNull(bean.getZipcode())); 
	 } 

	 @Override
	 public int Delete(ThmapModel bean) throws SQLException {

		 String sql = "DELETE FROM thmap WHERE THmapId = ? "; 

		 return db.remove(sql,bean.getThmapid()); 
	 } 

	 @Override
	 public int Update(ThmapModel bean) throws SQLException {

		 String sql = "UPDATE thmap SET `PROVINCE` = ?, `AMPHUR` = ?, " + 
 					 "`TAMBON` = ?, `lat` = ?, `lng` = ?, " + 
 					 "`zipcode` = ? WHERE `THmapId` = ?"; 

		 return db.update(sql, bean.getProvince(), bean.getAmphur(),  
 					 bean.getTambon(), bean.getLat(), bean.getLng(),  
 					 CheckNumber.formatNull(bean.getZipcode()), bean.getThmapid()); 
	 } 

	 @Override
	 public ArrayList<ThmapModel> FindAll() throws SQLException {

		 ArrayList<ThmapModel> result = new ArrayList<ThmapModel>();

		 String sql = "SELECT * FROM thmap ORDER BY PROVINCE"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 } 

	 @Override
	 public ThmapModel FindByID(ThmapModel bean) throws SQLException {

		 ThmapModel result = new ThmapModel();

		 String sql = "SELECT * FROM thmap WHERE THmapId = ?";
		 ResultMap querySingle = db.querySingle(sql, bean.getThmapid()); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle);
		 } 

		 return result; 
	 } 

	 @Override
	 public ThmapModel FindByID(int id) throws SQLException {

		 ThmapModel result = new ThmapModel();

		 String sql = "SELECT * FROM thmap WHERE THmapId = ?";
		 ResultMap querySingle = db.querySingle(sql, id); 

		 if(!querySingle.isEmpty()) {
			 result = MappingBeans(querySingle); 
		 } 

		 return result; 
	 } 

	 @Override
	 public ThmapModel FindByUUID(String uuid) throws SQLException {

		 return null; 
	 } 

	 @Override
	 public ThmapModel MappingBeans(ResultMap map) throws SQLException {

		ThmapModel model = new ThmapModel(); 

		model.setThmapid(map.get("THmapId") != null ? Integer.parseInt(map.get("THmapId")) : Integer.MIN_VALUE); 
		model.setProvince(map.get("PROVINCE")); 
		model.setAmphur(map.get("AMPHUR")); 
		model.setTambon(map.get("TAMBON")); 
		model.setLat(map.get("lat")); 
		model.setLng(map.get("lng")); 
		model.setZipcode(map.get("zipcode") != null ? Integer.parseInt(map.get("zipcode")) : Integer.MIN_VALUE); 

		return model; 
	 }
	 public ArrayList<ThmapModel> FindAllProvince() throws SQLException {

		 ArrayList<ThmapModel> result = new ArrayList<ThmapModel>();

		 String sql = "SELECT * FROM thmap\n" + 
		 		"GROUP BY PROVINCE\n" + 
		 		"ORDER BY PROVINCE"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<ThmapModel> FindAllAmphur(String amphur) throws SQLException {

		 ArrayList<ThmapModel> result = new ArrayList<ThmapModel>();

		 String sql = "SELECT * FROM thmap\n" + 
		 		"WHERE PROVINCE = ?\n" + 
		 		"GROUP BY AMPHUR\n" + 
		 		"ORDER BY AMPHUR"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,amphur); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
	 public ArrayList<ThmapModel> FindAllTambon(String tambon) throws SQLException {

		 ArrayList<ThmapModel> result = new ArrayList<ThmapModel>();

		 String sql = "SELECT * FROM thmap\n" + 
		 		"WHERE AMPHUR = ?\n" + 
		 		"GROUP BY TAMBON\n" + 
		 		"ORDER BY TAMBON"; 
		 ArrayList<ResultMap> queryList = db.queryList(sql,tambon); 

		 if(!queryList.isEmpty()) {
			 for (ResultMap map : queryList) {
				 result.add(MappingBeans(map)); 
			 }
		 } 

		 return result; 
	 }
}