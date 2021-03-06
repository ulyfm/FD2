package us.noop.server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection conn;
	
	private Statement stat;
	
	public Database(String dbname){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);
			conn.setAutoCommit(false);
			stat = conn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addTable(String tablename, String tablestat){
		String a = "create table if not exists " + tablename + " " + tablestat;
		try {
			stat.executeUpdate(a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getSelect(String tname, String cmd){
		try {
			return stat.executeQuery("SELECT " + cmd + " FROM " + tname + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(String tname, String cmd){
		try{
			stat.executeUpdate("INSERT INTO " + tname + " " + cmd);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Statement getStatement(){
		return stat;
	}
	
	public void update(String table, String cmd){
		try{
			stat.executeUpdate("UPDATE " + table + " set " + cmd);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void delete(String table, String cmd){
		try{
			stat.executeUpdate("DELETE from " + table + " " + cmd);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
