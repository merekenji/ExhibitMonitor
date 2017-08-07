package monitor.exhibit.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import monitor.exhibit.beans.Record;
import monitor.exhibit.utilities.ApplicationContext;

public class RecordDAO {
	public synchronized void insertValidRecord(Record record) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "C@pgemin!");
			ps = con.prepareStatement("INSERT INTO VALID_RECORD(FILENAME,ROWNUM,DATE,VALUE) VALUES(?,?,?,?)");
			ps.setString(1, record.getFileName());
			ps.setInt(2, record.getRowNum());
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getDate());
			ps.setDate(3, new Date(cal.getTimeInMillis()));
			ps.setString(4, record.getRecord());
			boolean execute = ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ApplicationContext.getValidRecords().remove(0);
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void insertInvalidRecord(Record record) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "C@pgemin!");
			ps = con.prepareStatement("INSERT INTO INVALID_RECORD(FILENAME,ROWNUM,DATE,VALUE) VALUES(?,?,?,?)");
			ps.setString(1, record.getFileName());
			ps.setInt(2, record.getRowNum());
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getDate());
			ps.setDate(3, new Date(cal.getTimeInMillis()));
			ps.setString(4, record.getRecord());
			boolean execute = ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ApplicationContext.getInvalidRecords().remove(0);
			try {
				if(ps != null) {
					ps.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
